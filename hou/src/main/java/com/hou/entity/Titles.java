package com.hou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName titles
 */
@TableName(value ="titles")
public class Titles implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String contenta;

    /**
     * 
     */
    private String contentb;

    /**
     * 
     */
    private String contentc;

    /**
     * 
     */
    private String contentd;

    /**
     * 
     */
    private String answer;

    /**
     * 
     */
    private String belong;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     */
    public String getContenta() {
        return contenta;
    }

    /**
     * 
     */
    public void setContenta(String contenta) {
        this.contenta = contenta;
    }

    /**
     * 
     */
    public String getContentb() {
        return contentb;
    }

    /**
     * 
     */
    public void setContentb(String contentb) {
        this.contentb = contentb;
    }

    /**
     * 
     */
    public String getContentc() {
        return contentc;
    }

    /**
     * 
     */
    public void setContentc(String contentc) {
        this.contentc = contentc;
    }

    /**
     * 
     */
    public String getContentd() {
        return contentd;
    }

    /**
     * 
     */
    public void setContentd(String contentd) {
        this.contentd = contentd;
    }

    /**
     * 
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 
     */
    public String getBelong() {
        return belong;
    }

    /**
     * 
     */
    public void setBelong(String belong) {
        this.belong = belong;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Titles other = (Titles) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getContenta() == null ? other.getContenta() == null : this.getContenta().equals(other.getContenta()))
            && (this.getContentb() == null ? other.getContentb() == null : this.getContentb().equals(other.getContentb()))
            && (this.getContentc() == null ? other.getContentc() == null : this.getContentc().equals(other.getContentc()))
            && (this.getContentd() == null ? other.getContentd() == null : this.getContentd().equals(other.getContentd()))
            && (this.getAnswer() == null ? other.getAnswer() == null : this.getAnswer().equals(other.getAnswer()))
            && (this.getBelong() == null ? other.getBelong() == null : this.getBelong().equals(other.getBelong()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getContenta() == null) ? 0 : getContenta().hashCode());
        result = prime * result + ((getContentb() == null) ? 0 : getContentb().hashCode());
        result = prime * result + ((getContentc() == null) ? 0 : getContentc().hashCode());
        result = prime * result + ((getContentd() == null) ? 0 : getContentd().hashCode());
        result = prime * result + ((getAnswer() == null) ? 0 : getAnswer().hashCode());
        result = prime * result + ((getBelong() == null) ? 0 : getBelong().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", contenta=").append(contenta);
        sb.append(", contentb=").append(contentb);
        sb.append(", contentc=").append(contentc);
        sb.append(", contentd=").append(contentd);
        sb.append(", answer=").append(answer);
        sb.append(", belong=").append(belong);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}