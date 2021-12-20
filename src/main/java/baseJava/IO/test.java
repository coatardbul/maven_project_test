//package baseJava.IO;
//
//import java.util.Scanner;
//
//import java.lang.Thread;
//
//import Ths.JDIBridge;
//
//public class test  {
//
//public static void main(String[] args) {
//
//		System.out.println(System.getProperty("java.library.path"));
//		System.load("E://bin//x64//iFinDJava_x64.dll");
//		int ret = -1;
//		if (args.length > 0) {
//			System.out.println("login with cn account");
//		}
//		 else {
//				System.out.println("login with en account");
//			}
//
//		int a = 0;
//		if (ret != 1) {
//			while(true)
//			{
//			System.out.print(++a);
//			ret = JDIBridge.THS_iFinDLogin("�˺�", "����");
//			System.out.println("THS_iFinDLogin ==> ");
//
//
//
//			String strResultDataSerious = JDIBridge.THS_DateSerial("002233.SZ","ths_open_price_stock;ths_high_price_stock;ths_low_stock;ths_close_price_stock;ths_avg_price_stock;ths_vol_stock;ths_trans_num_stock;ths_amt_stock;ths_macd_stock;ths_kdj_stock;ths_vstd_stock;ths_boll_stock;ths_rsi_stock;ths_ma_stock;ths_sar_stock;ths_wr_stock;ths_cci_stock;ths_obv_stock;ths_vol_w_stock;ths_vol_m_stock","100;100;100;100;100;100;;;26,12,9,100,100,100;9,3,3,100,100,100;10,100;26,2,100,100,100;6,100,100;10,100,100;4,100,100;14,100,100;14,100,100;100,100,100;;","Days:Tradedays,Fill:Previous,Interval:D","2018-05-31","2018-06-15");
//			System.out.println("THS_iFinDhis ==> " + strResultDataSerious );
//
//
//			JDIBridge.THS_iFinDLogout();
//			System.out.println("THS_iFinDLogout ==> ");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			}
//		}
//		else {
//			System.out.println("Login failed == > " + ret);
//		}
//	}
//
//}