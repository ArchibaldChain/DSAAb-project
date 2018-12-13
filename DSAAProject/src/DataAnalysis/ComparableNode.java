package DataAnalysis;

/**
 * ProjectName:    DSAAProject
 * Author:         Archibald Chain
 * CreateDate:     2018/12/13 20:03
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

abstract class ComparableNode<T>{

    /**
     * if there is comparable value larger than the in o, return true
     * @param o is the same object of this
     * @return lager than true; less or equal than return false
     */
    abstract boolean compareTo(T o);


}
