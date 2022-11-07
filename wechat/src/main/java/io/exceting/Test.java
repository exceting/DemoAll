package io.exceting;

import io.exceting.api.QuoraService;

public class Test {

    public static void main(String[] args) throws Exception {
        String html = QuoraService.INSTANT.getAnswer("Why-is-Russia-considered-a-better-military-power-than-China-They-have-less-troops-manpower-finances-and-arguably-worse-equivalent-tech", "Tywin-Rokana");
        System.out.println(html);
    }


}
