package com.brotherhui.tccpoc.participant.core;

import java.util.concurrent.atomic.AtomicLong;  

//just for test propose
public class SequenceID {  
    private static AtomicLong sequenceid = new AtomicLong(0);  
    private static SnowFlake snowFlake = new SnowFlake(1, 1);
    
    public static long nextID() {  
//        return sequenceid.getAndIncrement();  
    	return snowFlake.nextId();
    }  

    
 
} 