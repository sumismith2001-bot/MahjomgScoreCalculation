package com.example.demo;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ScoreService{
	
    private final OyaScoreRepository oyaRepo;
    private final KoScoreRepository koRepo;
    private final LimitScoreRepository limitRepo;
	


    // 入力項目
    String jicha;                     // 親 / 子
    int honba;                        // 本場
    int han;                          // 翻
    String agari ="その他";                     // ピンフ・七対子など
    String agarikata;                 // ツモ / 門前ロン
    String machi;                    // 待ち
    String janto;                     // 雀頭

    int minko_chunchanpai = 0;            // 明刻子（中張牌）
    int minko_yaochūhai = 0;              // 明刻子（么九牌）
    int anko_chunchanpai = 0;             // 暗刻子（中張牌）
    int anko_yaochūhai = 0;               // 暗刻子（么九牌）
    int minkan_chunchanpai = 0;           // 明槓子（中張牌）
    int minkan_yaochūhai = 0;             // 明槓子（么九牌）
    int ankan_chunchanpai = 0;            // 暗槓子（中張牌）
    int ankan_yaochūhai = 0;              // 暗槓子（么九牌）

    // 計算結果
    int fu = 20;                      // 符（計算後に上書き）
    int mainPoint;                    // 最終表示点
    int ronPay;                       // ロン時の支払い点
    int parentPay;                    // ツモ時：親の支払い
    int childPay;                     // ツモ時：子の支払い
    String rankName;                  // 満貫・跳満・倍満・三倍満・役満

    // ============================================================
    // ここから符計算＋得点計算ロジック
    // ============================================================
    
    
    public void calculate() {
    	
    	int fu  = fu_calculate();
    	
        // -------------------------
        // 得点計算
        // -------------------------
        boolean parent = jicha.equals("親");
        boolean tsumo = agarikata.equals("ツモ");
        
       

        rankName = "";


        if (han >= 13) {

            rankName = "役満";
        } else if (han >= 11) {

            rankName = "三倍満";
            
        } else if (han >= 8) {

            rankName = "倍満";
        } else if (han >= 6) {

            rankName = "跳満";
        } else if (han >= 5 ){
        	
            rankName = "満貫";

        } else {
        	//親
        	if(parent) {
	            OyaScore os = oyaRepo.findByFuAndHan(fu, han);
		        //ツモ
		        if(tsumo) {
		        	childPay = os.getTsumoChild();
		        	mainPoint = childPay*3;
		        }else{      	
		        	ronPay = os.getRon();
		        	mainPoint = ronPay;
		       }
	        //子     	
	        }else{
	            KoScore ks = koRepo.findByFuAndHan(fu, han);
	        	//ツモ
	        	if(tsumo) {
		        	childPay = ks.getTsumoChild();
		        	parentPay = ks.getTsumoParent();
		        	mainPoint = childPay*2 + parentPay;
	        	}else {
	        		ronPay = ks.getRon(); 
	        		mainPoint = ronPay;
	        	}

	        }
    	}
        
        if(rankName != "") {
        	LimitScore lm = limitRepo.findByName(rankName);
        	
        	//親
        	if(parent) {
		        //ツモ
		        if(tsumo) {
		        	childPay = lm.getParentTsumoChild();
		        	mainPoint = childPay*3;
		        }else{      	
		        	ronPay = lm.getParentRon();
		        	mainPoint = ronPay;
		       }
	        //子     	
	        }else{
	        	//ツモ
	        	if(tsumo) {
		        	childPay = lm.getChildTsumoChild();
		        	parentPay = lm.getChildTsumoParent();
		        	mainPoint = childPay*2 + parentPay;
		        	
	        	}else {
	        		ronPay = lm.getChildRon();
	        		mainPoint = ronPay;
	        	}
	        }
        
        }
        
        
    }
        
    

    public int fu_calculate() {

        fu = 20; // 基本符

        // -------------------------
        // アガリ形（ピンフ・七対子など）
        // -------------------------
        switch (agari) {
            case "ピンフ・ツモ":
                fu = 20;
                return fu;
            case "鳴きピンフ・ロン":
                fu = 30;
                return fu;
            case "七対子":
                fu = 25;
                return fu;
            case "その他":
            	fu = 20;
            	break;
        }

        // -------------------------
        // アガリ方（ツモ / 門前ロン）
        // -------------------------
        if (agarikata.equals("ツモ")) {
            fu += 2;
        } else if (agarikata.equals("門前・ロン")) {
            fu += 10;
        }

        // -------------------------
        // 待ち（gmachi）
        // -------------------------
        switch (machi) {
            case "ペンチャン":
            case "カンチャン":
            case "単騎":
                fu += 2;
                break;
            default:
                break;
        }

        // -------------------------
        // 雀頭（janto）
        // -------------------------
        switch (janto) {
            case "役牌":
                fu += 2;
                break;
            case "役牌(ダブトン・ダブナン)":
                fu += 4;
                break;
        }

        // -------------------------
        // 面子（刻子・槓子）
        // -------------------------
        fu += minko_chunchanpai * 2;
        fu += minko_yaochūhai * 4;

        fu += anko_chunchanpai * 4;
        fu += anko_yaochūhai * 8;

        fu += minkan_chunchanpai * 8;
        fu += minkan_yaochūhai * 16;

        fu += ankan_chunchanpai * 16;
        fu += ankan_yaochūhai * 32;

        // -------------------------
        // 本場（honba）
        // -------------------------
        fu += honba;

        // -------------------------
        // 符は10の位に切り上げ
        // -------------------------
        fu = ((fu + 9) / 10) * 10;
        
        return fu;
        
    }

}
