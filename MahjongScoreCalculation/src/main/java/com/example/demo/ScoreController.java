package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @RequestMapping("/")
    public String start() {
        return "input.html";
    }

    @RequestMapping("/score")
    public ModelAndView register(@ModelAttribute ScoreService ss, ModelAndView m) {

        // ★ ScoreService のインスタンスは DI されたものを使う
        scoreService.setJicha(ss.getJicha());
        scoreService.setHonba(ss.getHonba());
        scoreService.setHan(ss.getHan());
        scoreService.setAgari(ss.getAgari());
        scoreService.setAgarikata(ss.getAgarikata());
        scoreService.setMachi(ss.getMachi());
        scoreService.setJanto(ss.getJanto());

        scoreService.setMinko_chunchanpai(ss.getMinko_chunchanpai());
        scoreService.setMinko_yaochūhai(ss.getMinko_yaochūhai());
        scoreService.setAnko_chunchanpai(ss.getAnko_chunchanpai());
        scoreService.setAnko_yaochūhai(ss.getAnko_yaochūhai());
        scoreService.setMinkan_chunchanpai(ss.getMinkan_chunchanpai());
        scoreService.setMinkan_yaochūhai(ss.getMinkan_yaochūhai());
        scoreService.setAnkan_chunchanpai(ss.getAnkan_chunchanpai());
        scoreService.setAnkan_yaochūhai(ss.getAnkan_yaochūhai());

        // ★ 計算実行
        scoreService.calculate();

        // ★ 計算結果を画面へ渡す
        m.addObject("ss", scoreService);
        m.setViewName("score.html");
        return m;
    }
}
