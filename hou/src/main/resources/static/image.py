# -*- codeing = utf-8 -*-
import requests
import re
import json
import bs4
from pprint import pprint
import subprocess

#发送
def GetResponse(url):
    headers={
        "Cookie":"LIVE_BUVID=AUTO4116479082319127; buvid3=AA8A6C24-BCF2-2802-34CF-BA9076522A8608045infoc; buvid4=0E1BABD9-B930-7085-EDAA-3CECBEE2F26308045-022072121-w5t72nCohoioAFrlQBK8vQ%3D%3D; hit-new-style-dyn=1; hit-dyn-v2=1; fingerprint=8abf5631c01e2d82c7d0bd77cb60f637; buvid_fp=4c89f362e1537e439636337b738b0558; header_theme_version=CLOSE; CURRENT_BLACKGAP=0; b_nut=100; CURRENT_FNVAL=4048; PVID=1; enable_web_push=DISABLE; DedeUserID=412778123; DedeUserID__ckMd5=0a22d501a7f5b897; CURRENT_QUALITY=80; FEED_LIVE_VERSION=V8; rpdid=0zbfAHUsRE|Hsos8sZa|DS3|3w1RTnPF; bp_video_offset_412778123=919047074945695781; _uuid=5526B1810-CB45-68CA-46D1-DD71C864F110C55833infoc; SESSDATA=47ea8b92%2C1730362259%2C8d2e1%2A51CjBfal3XcAfhO5RXMMxk8ASicVxiashhwIbYY3VYXce-AAOXYNMo8gq2oVBor6Nf_Z4SVjhicW9INHdZLVFtVU95NWRiVy1oVGc0eEdVaTZoeWF5N2s3MFFQUkhydHJiN19EWjJETFlUYU1PdURNdkRRNjR3ejk4Rk16bHMzMV83NmpzTGtyTm9RIIEC; bili_jct=d36e5d216397607b9be5f42e7670ad57; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTUwNjk0NjcsImlhdCI6MTcxNDgxMDIwNywicGx0IjotMX0.xt1gaoJJi3uMEuFwmVjHtj9ygPog3RdJjKNAVbVpiAA; bili_ticket_expires=1715069407; bmg_af_switch=1; bmg_src_def_domain=i1.hdslb.com; sid=6o52h9qr; home_feed_column=4; browser_resolution=719-670; b_lsid=31A7FD42_18F4D0200A5; bp_t_offset_412778123=928347324106670080",
        "Referer":"https://cn.bing.com/",
        "User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 Edg/124.0.0.0",
    }
    response=requests.get(url=url,headers=headers)
    return response

#获取数据
def GetVideoInfo():
    link='https://www.bilibili.com/video/BV1Zy4y1x73G/?spm_id_from=333.337.search-card.all.click'
    response=GetResponse(url=link)
    html=response.text
    print(html)
    #解析数据
    #音视频信息
    info=re.findall('<script>window.__playinfo__=(.*?)</script>',html)[0]
    print(info)
    json_data=json.loads(info)
    audio_url=json_data['data']['dash']['audio'][0]['baseUrl']
    video_url=json_data['data']['dash']['video'][0]['baseUrl']
    # image_url=re.findall('<script type="text/javascript">window.__pinia=(function(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,_,$,aa,ab,ac,ad,ae,af,ag,ah,ai,aj,ak,al,am,an,ao,ap,aq,ar,as,at,au,av,aw,ax,ay,az,aA,aB,aC,aD,aE,aF,aG,aH,aI,aJ,aK,aL,aM,aN,aO,aP,aQ,aR,aS,aT,aU,aV,aW,aX,aY,aZ,a_,a$,ba,bb,bc,bd,be,bf,bg,bh,bi,bj,bk,bl,bm,bn,bo,bp,bq,br,bs,bt,bu,bv,bw,bx,by,bz,bA,bB,bC,bD,bE,bF,bG,bH,bI,bJ,bK,bL,bM,bN,bO,bP,bQ,bR,bS,bT,bU,bV,bW,bX,bY,bZ,b_,b$,ca,cb,cc,cd,ce,cf,cg,ch,ci,cj,ck,cl,cm,cn,co,cp,cq,cr,cs,ct,cu,cv,cw,cx,cy,cz,cA,cB,cC,cD,cE,cF,cG,cH,cI,cJ,cK,cL,cM,cN,cO,cP,cQ,cR,cS,cT,cU,cV,cW,cX,cY,cZ,c_,c$,da,db,dc,dd,de,df,dg,dh,di,dj,dk,dl,dm,dn,do0,dp,dq,dr,ds,dt,du,dv,dw,dx,dy,dz,dA,dB,dC,dD,dE,dF,dG,dH,dI,dJ,dK,dL,dM,dN,dO,dP,dQ,dR,dS,dT,dU,dV,dW,dX,dY,dZ,d_,d$,ea,eb,ec,ed,ee,ef,eg,eh,ei,ej,ek,el,em,en,eo,ep,eq,er,es,et,eu,ev,ew,ex,ey,ez,eA,eB,eC,eD,eE,eF,eG,eH,eI,eJ,eK,eL,eM,eN,eO,eP,eQ,eR,eS,eT,eU,eV,eW,eX,eY,eZ,e_,e$,fa,fb,fc)',html)
    title=re.findall('class="video-title special-text-indent" data-v-5120f6b9>(.*?)</h1>',html)[0].replace(' ','')
    return title,audio_url,video_url


def Save(title,audio_url,video_url,image_url):
    audio_content=GetResponse(url=audio_url).content
    video_content=GetResponse(url=video_url).content
    decode_url = image_url.encode('utf-8').decode("unicode_escape")
    if not decode_url.startswith(('http:', 'https:')):
        decode_url = 'http:' + decode_url
    response = requests.get(decode_url)
    with open('image\\'+title+'.jpg',mode='wb') as image:
        image.write(response.content)
    with open('video\\'+title+'.mp3',mode='wb') as audio:
        audio.write(audio_content)
    with open('video\\'+title+'.mp4',mode='wb') as video:
        video.write(video_content)
    cmd = f"ffmpeg -i video\\{title}.mp4 -i video\\{title}.mp3 -c:v copy -c:a aac -strict experimental data\\{title}.mp4"
    subprocess.run(cmd)

if __name__=='__main__':
    # title,audio_url,video_url,image_url=
    GetVideoInfo()
    # Save(title,audio_url,video_url,image_url)


#合并
