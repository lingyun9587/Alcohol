package com.alcohol.util;

import pro.mickey.MickeySnowflake;

public class IDUtil {
    private static MickeySnowflake mickeySnowflake;
	private  Integer bianhao = 0;
	private static IDUtil idUtil;
	private IDUtil() {}
	private static IDUtil getIDUtil() {
		if(idUtil == null) {
			idUtil = new IDUtil();
		}
		return idUtil;
	}
	private Integer getBianhao() {
		return bianhao;
	}
	private void setBianhao(Integer bianhao) {
		this.bianhao = bianhao;
	}
	public  synchronized static String getBianHao() {
		getIDUtil();
		IDUtil.idUtil.setBianhao(IDUtil.idUtil.getBianhao()+1);
		String seq = String.format("DS_%08d", IDUtil.getIDUtil().getBianhao());
		return seq;
	}
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(new IDUtil().getBianHao());
		}
		
		
	}
    /**
     * snowflake生成方式
     * @return
     */
    public static  Long SnowflakeIdWorker() {
        if(mickeySnowflake == null) {
            mickeySnowflake = new MickeySnowflake(123);
        }
        return mickeySnowflake.generateKey();
    }
	
}
