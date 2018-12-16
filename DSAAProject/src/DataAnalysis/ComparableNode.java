package DataAnalysis;

/**
 * ProjectName:    DSAAProject
 * Author:         Archibald Chain
 * CreateDate:     2018/12/13 20:03
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

public abstract class ComparableNode<T extends ComparableNode>{

    /**
     * if there is comparable value larger than the value in o, return true
     * smaller or equal return false
     * @param o is the same object of this
     * @return lager than true; less or equal than return false
     */

    int num;
    // The number is the value used for comparing

    boolean compareTo(T o){
        return this.num <= o.num;
    }

    boolean equals(T o){
        return this.num == o.num;
    }
}
