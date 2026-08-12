document.addEventListener("input", calculateFu);

function calculateFu() {
    let fu = 20;

    const agari = document.querySelector('input[name="agari"]:checked').value;
    if (agari === "ピンフ・ツモ") fu = 20;
    else if (agari === "鳴きピンフ・ロン") fu = 30;
    else if (agari === "七対子") fu = 25;
    else {

	    const agarikata = document.querySelector('input[name="agarikata"]:checked').value;
	    if (agarikata === "ツモ") fu += 2;
	    else if (agarikata === "門前・ロン") fu += 10;
	
	    const machi = document.querySelector('input[name="machi"]:checked').value;
	    if (["ペンチャン", "カンチャン", "単騎"].includes(machi)) fu += 2;
	
	    const janto = document.querySelector('input[name="janto"]:checked').value;
	    if (janto === "役牌") fu += 2;
	    else if (janto === "役牌(ダブトン・ダブナン)") fu += 4;
	
	    fu += getNumber("minko_chunchanpai") * 2;
	    fu += getNumber("minko_yaochūhai") * 4;
	    fu += getNumber("anko_chunchanpai") * 4;
	    fu += getNumber("anko_yaochūhai") * 8;
	    fu += getNumber("minkan_chunchanpai") * 8;
	    fu += getNumber("minkan_yaochūhai") * 16;
	    fu += getNumber("ankan_chunchanpai") * 16;
	    fu += getNumber("ankan_yaochūhai") * 32;
	}
    document.getElementById("fu-result").textContent = `合計符：${fu}符`;
}

function getNumber(name) {
    return Number(document.querySelector(`input[name="${name}"]`).value || 0);
}

// ★ これを追加する
calculateFu();
