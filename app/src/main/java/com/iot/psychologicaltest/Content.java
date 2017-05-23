package com.iot.psychologicaltest;

import java.io.Serializable;

public class Content implements Serializable
{
    int q_num;

    public Content(){
    }

    public Content(int q_num)
    {
        this.q_num = q_num;
    }

    public int getQ_num()
    {
        return q_num;
    }

    public void setQ_num(int q_num)
    {
        this.q_num = q_num;
    }
}
