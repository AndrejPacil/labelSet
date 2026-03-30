    import java.io.File;
    import java.io.FileNotFoundException;
    import java.sql.SQLOutput;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Scanner;
    
    public class labelSet {
    
        //key bude zaciatocna vrchol a AL bude obsahovat konciaci vrchol a aj cenu
        private HashMap<Integer , HashMap<Integer, Integer>> zoznamHran;
    
    
        private Scanner skener;
        public labelSet() {
            this.zoznamHran = new HashMap<>();
            try {
                this.citaj();
            } catch (Exception e){
                return;
    
            }
            this.prechadzaj();
    
    
        }
    
    
        public void citaj() {
            this.skener = new Scanner(System.in);
            System.out.println("Zadaj názov súboru: ");
            var nazov = "src/" + this.skener.nextLine();
            try {
                this.skener = new Scanner(new File(nazov));
            } catch (FileNotFoundException e) {
    
                System.out.println("Súbor sa nenašiel");
    
            }
    
            var pocitadloPoradia = 1;
            var zaciatocnaHrana = 0;
            var koncovaHrana = 0;
    
    
            while(this.skener.hasNextInt()) {
    
                if(pocitadloPoradia > 3){
                    pocitadloPoradia = 1;
                }
    
                var nasledujuci = this.skener.nextInt();
                if(pocitadloPoradia == 1){
    
                        if (this.zoznamHran.get(nasledujuci) == null){
                            this.zoznamHran.put(nasledujuci, new HashMap<Integer, Integer>());
                        }
                        zaciatocnaHrana = nasledujuci;
    
                }else if(pocitadloPoradia == 2){
                        koncovaHrana = nasledujuci;
                }else{
                        var dataHrany = this.zoznamHran.get(zaciatocnaHrana);
                        dataHrany.put(koncovaHrana, nasledujuci);
                }
                pocitadloPoradia++;
            }
        }
    

        public void prechadzaj(){
    
    
            this.skener = new Scanner(System.in);
            System.out.println("Zadaj začinajuci vrchol: ");
            var u = this.skener.nextInt();
            System.out.println("Zadaj koncový vrchol: ");
            var v = this.skener.nextInt();
    
    
                //Vrcholy pri ktorych sa uz znacky nemenia = e
                var e = new ArrayList<Integer>();
                //Aktualne najlepsie najdene ceny ciest = t
                var t = new HashMap<Integer, Integer>();
                //Cesty ktore neboli vybrate z dôvodu vyberu cesty ktorá ma nižšiu cenu = x
                var x = new HashMap<Integer, Integer>();
    
    
                t.put(u, 0);
                // aktualny vrchol = r
                var r = u;
    
                while(true) {
    
                    e.add(r);
                    var hrany = this.zoznamHran.get(r);
    
                    if (hrany != null) {
                        for (int cislo : hrany.keySet()) {
                            //sused = j
                            var j = cislo;
                            //susedCena = c
                            var c = hrany.get(cislo);
                            var novaCena = t.get(r) + c;
    
                            if(!t.containsKey(j) ||novaCena  < t.get(j) ){
                                t.put(j, novaCena);
                                x.put(j, r);
                            }
                        }
                    }
    
                    if(r == v) {
                        break;
                    }
    
                    var dalsiVrchol = -1;
                    var najnizsiaCena = Integer.MAX_VALUE;
    
                    for(int cislo : t.keySet()){
    
                        var vrchol = cislo;
                        var cena =  t.get(cislo);
    
                        if(!e.contains(cislo) && cena < najnizsiaCena){
                            dalsiVrchol = vrchol;
                            najnizsiaCena = cena;
                        }
                    }
    
                    if (dalsiVrchol == -1 ){
                        System.out.println("V digrafe sa neneachadza takáto cesta");
                        return;
                    }
                    r = dalsiVrchol;
    
                }
            System.out.println("Najkratsia vzdialenost z "+ u + " do " + v + " je " + t.get(v));
            var cesta = new ArrayList<Integer>();
    
            var prvok = v;
    
            while (prvok != u){
    
                cesta.add(0, prvok);
                prvok = x.get(prvok);
    
            }
    
            cesta.add(0, u);
            System.out.print("(");
            for(var hodnota : cesta){
                if(cesta.indexOf(hodnota) == cesta.size() -1){
                    System.out.print(" "+hodnota);
                    break;
                }
                System.out.print(" "+hodnota+",");
            }
            System.out.print(")");
    
    
        }
    
    
    
    
    }
