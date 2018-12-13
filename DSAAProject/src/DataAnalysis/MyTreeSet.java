package DataAnalysis;

import java.util.AbstractSet;
import java.util.Iterator;


/*
  ProjectName: DSAAProject
  Author: Archibald Chain
  CreateDate: 2018/12/13 19:24
  Version: 1.0
  <p>Copyright: Copyright (c) 2018</p>
 */

/**
 * It is using heap to implement TreeSet
 * @param <E>
 */
public class MyTreeSet<E extends ComparableNode<E>> extends AbstractSet<E> {

    private MyHeap<E> elementData;


    public MyTreeSet() {
        elementData = new MyHeap<>();
    }

    public boolean add(E e){
        elementData.add(e);
        return true;

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return size() > 0;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new IndexOutOfBoundsException();
                return elementData.delete();
            }


        };
    }

    @Override
    public int size() {
        return elementData.getSize();
    }
}

