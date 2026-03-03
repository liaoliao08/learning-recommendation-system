# -*- codeing = utf-8 -*-
import requests
import re
import json
import bs4
from pprint import pprint
import subprocess
from DrissionPage import ChromiumPage
#发送
def GetResponse(url):
    headers={
        "Cookie":"LIVE_BUVID=AUTO4116479082319127; buvid3=AA8A6C24-BCF2-2802-34CF-BA9076522A8608045infoc; buvid4=0E1BABD9-B930-7085-EDAA-3CECBEE2F26308045-022072121-w5t72nCohoioAFrlQBK8vQ%3D%3D; hit-new-style-dyn=1; hit-dyn-v2=1; fingerprint=8abf5631c01e2d82c7d0bd77cb60f637; buvid_fp=4c89f362e1537e439636337b738b0558; header_theme_version=CLOSE; CURRENT_BLACKGAP=0; b_nut=100; CURRENT_FNVAL=4048; enable_web_push=DISABLE; DedeUserID=412778123; DedeUserID__ckMd5=0a22d501a7f5b897; CURRENT_QUALITY=80; FEED_LIVE_VERSION=V8; rpdid=0zbfAHUsRE|Hsos8sZa|DS3|3w1RTnPF; bp_video_offset_412778123=919047074945695781; _uuid=5526B1810-CB45-68CA-46D1-DD71C864F110C55833infoc; SESSDATA=47ea8b92%2C1730362259%2C8d2e1%2A51CjBfal3XcAfhO5RXMMxk8ASicVxiashhwIbYY3VYXce-AAOXYNMo8gq2oVBor6Nf_Z4SVjhicW9INHdZLVFtVU95NWRiVy1oVGc0eEdVaTZoeWF5N2s3MFFQUkhydHJiN19EWjJETFlUYU1PdURNdkRRNjR3ejk4Rk16bHMzMV83NmpzTGtyTm9RIIEC; bili_jct=d36e5d216397607b9be5f42e7670ad57; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTUwNjk0NjcsImlhdCI6MTcxNDgxMDIwNywicGx0IjotMX0.xt1gaoJJi3uMEuFwmVjHtj9ygPog3RdJjKNAVbVpiAA; bili_ticket_expires=1715069407; sid=6o52h9qr; home_feed_column=5; browser_resolution=1488-716; b_lsid=5A1919A7_18F4DFA1C4A; bp_t_offset_412778123=928409661289267223; PVID=4",
        "Referer":"https://space.bilibili.com/1329324553/search/video?keyword=%E5%95%86%E5%8A%A1",
        "User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 Edg/124.0.0.0",
    }
    response=requests.get(url=url,headers=headers)
    return response

#获取数据
def GetVideoInfo(bv):
    link=f'https://www.bilibili.com/video/{bv}/?spm_id_from=333.999.0.0'
    response=GetResponse(url=link)
    html=response.text
    #解析数据
    #音视频信息
    info=re.findall('<script>window.__playinfo__=(.*?)</script>',html)[0]
    json_data=json.loads(info)
    audio_url=json_data['data']['dash']['audio'][0]['baseUrl']
    video_url=json_data['data']['dash']['video'][0]['baseUrl']
    title=re.findall('class="video-title special-text-indent" data-v-5120f6b9>(.*?)</h1>',html)[0].replace(' ','')
    character = '\/:*?"<>|'
    for s in character:
        if s in title:
            title = title.replace(s, '')

    return title,audio_url,video_url



def Save(title,audio_url,video_url,image_url):
    audio_content=GetResponse(url=audio_url).content
    video_content=GetResponse(url=video_url).content
    if not image_url.startswith(('http:', 'https:')):
        image_url = 'http:' + image_url
    response = requests.get(image_url)
    with open('image\\'+title+'.jpg',mode='wb') as image:
        image.write(response.content)
    with open('video\\'+title+'.mp3',mode='wb') as audio:
        audio.write(audio_content)
    with open('video\\'+title+'.mp4',mode='wb') as video:
        video.write(video_content)
    cmd = f"ffmpeg -i video\\{title}.mp4 -i video\\{title}.mp3 -c:v copy -c:a aac -strict experimental data\\{title}.mp4"
    subprocess.run(cmd)

if __name__=='__main__':
    driver=ChromiumPage()
    driver.listen.start('https://api.bilibili.com/x/space/wbi/arc/search')
    driver.get('https://space.bilibili.com/99744542/video')
    resp=driver.listen.wait()
    JsonData=resp.response.body
    for index in JsonData['data']['list']['vlist']:
        bv=index['bvid']
        img_url=index['pic']
        title,audio_url,video_url=GetVideoInfo(bv=bv)
        print(title,video_url)
        Save(title,audio_url,video_url,img_url)

