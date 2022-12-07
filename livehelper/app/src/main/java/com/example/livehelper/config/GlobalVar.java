package com.zrkaxt.testmachine.config;

import com.zrkaxt.testmachine.obj.Advertising;
import com.zrkaxt.testmachine.obj.InstInfo;
import com.zrkaxt.testmachine.obj.Member;

import java.util.ArrayList;
import java.util.List;

public class GlobalVar {

    public static boolean ThreadFlag =true;

    public static long AdvertisingInterval(){
        return 5*60;
    }
    private static String mac="";
    public static void SetMac(String _mac){
        mac=_mac;
    }
    public static String GetMac(){
        return mac;
    }

    public  static  int  GetMemberid(){
        return GlobalVar.getMember() == null ? 0 : GlobalVar.getMember().getId();
    }

    public static  String openid="";

    public static InstInfo getInstInfo() {
        return InstInfo;
    }

    public static Member getMember() {
        return member;
    }

    public static String getopenid() {
        return openid;
    }
    public static void Setopenid(String _openid){
        openid=_openid;
    }

    public static void setInstInfo(InstInfo instinfo) {
         InstInfo=instinfo;
    }

    private static Member member;

    private static InstInfo InstInfo;


    public static List<Advertising> GetAdvertisingList() {
        return AdvertisingList;
    }

    public static void SetAdvertisingList(List<Advertising> advertisingList) {
        AdvertisingList = advertisingList;
    }

    private static List<Advertising> AdvertisingList=new ArrayList<>();


    public static void setMember(Member member) {
        GlobalVar.member = member;
    }
}
