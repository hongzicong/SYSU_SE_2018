public class OccupantInCol
{
    // the object in it
    private Object mOccupant;
    private int mCol;
    
    // constructor for OccupantIncol class
    public OccupantInCol(Object occupant,int col){
        mOccupant = occupant;
        mCol = col;
    }

    // getter for occupant
    public Object getOccupant(){
        return mOccupant;
    }

    // getter for col
    public int getCol(){
        return mCol;
    }

    // setter for occupant
    public void setOccupant(int occupant){
        mOccupant = occupant;
    }

    // setter for col
    public void setCol(int col){
        mCol = col;
    }

}