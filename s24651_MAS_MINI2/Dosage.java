public class Dosage extends ObjectPlusPlus{
    int quantityPerPeriod;
    Periods perPeriod;
    int periodLength;
    Periods forThePeriod;

    public Dosage(int quantityPerPeriod, Periods perPeriod, int periodLength, Periods forThePeriod){
        super();
        this.quantityPerPeriod = quantityPerPeriod;
        this.perPeriod = perPeriod;
        this.periodLength = periodLength;
        this.forThePeriod = forThePeriod;
    }

    public Dosage(int quantityPerPeriod, Periods perPeriod){
        super();
        this.quantityPerPeriod = quantityPerPeriod;
        this.perPeriod = perPeriod;
    }

    @Override
    public String toString() {
        if (forThePeriod == null || periodLength <= 0){
            return quantityPerPeriod + " time(s) a " + perPeriod;
        }
        return quantityPerPeriod + " time(s) a " + perPeriod + ", for " + periodLength + " " + forThePeriod + "(s)";
    }
}
