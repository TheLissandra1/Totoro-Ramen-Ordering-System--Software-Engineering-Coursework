import java.util.Arrays;

/**
 * Title : new.java
 * Description: This class represent one day's data
 * @author Zhaoxiao Su
 * @version 1.0
 */
public class DayData {
    Menu menu = new Menu();

    Integer[] spiciness;

    Integer[] soup;

    Integer[] noddles;

    Integer[] springOnion;

    Integer extraNori = 0;
    Integer extraBoiledEgg = 0;
    Integer bambooShoots = 0;
    Integer extraChashu = 0;
    Integer nori= 0;
    Integer chashu = 0;
    Integer boiledEgg = 0;

    public DayData() {
        spiciness = new Integer[menu.spicy.length-1];
        for (int i = 0; i < spiciness.length; i++) {
            spiciness[i] = 0;
        }
        soup = new Integer[menu.dishes[0].length-1];
        for (int i = 0; i < soup.length; i++){
            soup[i] = 0;
        }
        noddles = new Integer[menu.dishes[1].length-1];
        for (int i = 0; i < noddles.length; i++){
            noddles[i] = 0;
        }
        springOnion = new Integer[menu.dishes[2].length-1];
        for (int i = 0; i < springOnion.length; i++){
            springOnion[i] = 0;
        }
    }
    
    public void addAll(DayData dayData){
        for (int i = 0; i < spiciness.length; i++) {
            spiciness[i] += dayData.spiciness[i];
        }
        
        for (int i = 0; i < soup.length; i++){
            soup[i] += dayData.soup[i];
        }
        
        for (int i = 0; i < noddles.length; i++){
            noddles[i] += dayData.noddles[i];
        }
       
        for (int i = 0; i < springOnion.length; i++){
            springOnion[i] += dayData.springOnion[i];
        }

        extraNori += dayData.extraNori;
        extraBoiledEgg += dayData.extraBoiledEgg;
        bambooShoots += dayData.bambooShoots;
        extraChashu += dayData.extraChashu;
        nori += dayData.nori;
        chashu += dayData.chashu;
        boiledEgg += dayData.boiledEgg;
    }

    @Override
    public String toString() {
        return "DayData{" +
                "spiciness=" + Arrays.toString(spiciness) +
                ", soup=" + Arrays.toString(soup) +
                ", noddles=" + Arrays.toString(noddles) +
                ", springOnion=" + Arrays.toString(springOnion) +
                ", extraNori=" + extraNori +
                ", extraBoiledEgg=" + extraBoiledEgg +
                ", bambooShoots=" + bambooShoots +
                ", extraChashu=" + extraChashu +
                ", nori=" + nori +
                ", chashu=" + chashu +
                ", boiledEgg=" + boiledEgg +
                '}';
    }

    public Integer[] getSpiciness() {
        return spiciness;
    }

    public void setSpiciness(Integer[] spiciness) {
        this.spiciness = spiciness;
    }

    public Integer[] getSoup() {
        return soup;
    }

    public void setSoup(Integer[] soup) {
        this.soup = soup;
    }

    public Integer[] getNoddles() {
        return noddles;
    }

    public void setNoddles(Integer[] noddles) {
        this.noddles = noddles;
    }

    public Integer[] getSpringOnion() {
        return springOnion;
    }

    public void setSpringOnion(Integer[] springOnion) {
        this.springOnion = springOnion;
    }

    public Integer getExtraNori() {
        return extraNori;
    }

    public void setExtraNori(Integer extraNori) {
        this.extraNori = extraNori;
    }

    public Integer getExtraBoiledEgg() {
        return extraBoiledEgg;
    }

    public void setExtraBoiledEgg(Integer extraBoiledEgg) {
        this.extraBoiledEgg = extraBoiledEgg;
    }

    public Integer getBambooShoots() {
        return bambooShoots;
    }

    public void setBambooShoots(Integer bambooShoots) {
        this.bambooShoots = bambooShoots;
    }

    public Integer getExtraChashu() {
        return extraChashu;
    }

    public void setExtraChashu(Integer extraChashu) {
        this.extraChashu = extraChashu;
    }

    public Integer getNori() {
        return nori;
    }

    public void setNori(Integer nori) {
        this.nori = nori;
    }

    public Integer getChashu() {
        return chashu;
    }

    public void setChashu(Integer chashu) {
        this.chashu = chashu;
    }

    public Integer getBoiledEgg() {
        return boiledEgg;
    }

    public void setBoiledEgg(Integer boiledEgg) {
        this.boiledEgg = boiledEgg;
    }
}
