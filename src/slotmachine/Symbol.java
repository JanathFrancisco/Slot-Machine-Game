/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;

/**
 *
 * @author win 8
 */
public class Symbol implements ISymbol{

    private String image=null;
    private int value;

    public Symbol(String image,int value){
        setImage(image);
        setValue(value);
    }
    public void setImage(String image){
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setValue(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }
}

