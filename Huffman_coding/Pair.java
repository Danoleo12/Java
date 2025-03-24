public class Pair implements Comparable<Pair> {

    private char value;
    private Double probability;

    public Pair(char value, Double probability){
        this.value = value;
        this.probability = probability;
    }
    

    public char getValue() {
        return value;
    }


    public void setValue(char value) {
        this.value = value;
    }


    public Double getProbability() {
        return probability;
    }


    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @Override
    public int compareTo (Pair p){ 

        return Double.compare( this.getProbability(),  p.getProbability());

    }


    @Override
    public String toString() {
        return "Pair [value=" + value + "; probability=" + probability + "]";
    }
    
}