package DataAnalysis;

import java.util.*;

/**
 * ProjectName:    DSAAProject
 * Author:         Archibald Chain
 * CreateDate:     2018/12/13 19:45
 * Version:        1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */

class MyHeap<T extends ComparableNode<T>> {

    private Object[] heap;
    private int size;

     MyHeap(){
        heap = new Object[16];
        size = 0;
    }

    private T heap(int index) {
        return (T) heap[index];
    }

    int getSize() {
        return size;
    }

    void add(T x){

        rangeCheck();
        heap[size] = x;

        size++;
        moveForward(size - 1);
    }

    private void moveForward(int n){
        int father, son = n;
        while(son > 0){
            father = (son-1)/2;
            if ((heap(father)).compareTo(heap(son)) ){
                exchange(father, son);
            }
            son = father;
        }
    }

     T delete() throws IndexOutOfBoundsException{
        if (size == 0){
            throw new IndexOutOfBoundsException("Line empty");
        }
        T temp = heap(0);
        heap[0] = heap[size - 1];

        heap[size - 1] = null;
        size --;
        if(size < heap.length/2)
            heap = Arrays.copyOf(heap, heap.length/2);

        moveBack(0);

        return temp;
    }

    public void changeNode(T node) throws NoSuchElementException{

        for (int i = 0; i < size; i++) {
            if (heap[i] == node){

                if (node.compareTo(heap(i))){
                    moveBack(i);
                } else {
                    moveForward(i);
                }
                return;
            }
        }
        throw new NoSuchElementException();
    }

    private void moveBack(int n){
        int father = n;
        int leftSon = n*2 + 1, rightSon = 2*n + 2;
        System.out.println(n);
        while (leftSon < size){
            T temp = min(heap(leftSon),
                    heap(rightSon));
            if (temp == null)
                break;
            else if (heap(father).compareTo( temp) ){
                if (heap(leftSon) == temp ){
                    exchange(father, leftSon);
                    father = leftSon;
                }
                else if (heap(rightSon) == temp){
                    exchange(father ,rightSon);
                    father = rightSon;
                }
            } else {
                break;
            }
            leftSon = father*2 + 1;
            rightSon = father*2 + 2;
        }
    }

    private void rangeCheck(){
        if (size >= heap.length)
            heap = Arrays.copyOf(heap,2*size + 2);
    }

    private void exchange(int a, int b){
        T temp = heap(a);
        heap[a] = heap(b);
        heap[b] = temp;
    }

    /**
     * the method is to compare two node
     * @param a the first node
     * @param b the second node
     * @return return the smaller T
     */
    private T min(T a, T b){
        if (b == null && a != null)
            return a;
        else if (a == null && b != null)
            return b;
        else if (a == null)
            return null;

        if (b.compareTo(a))
            return a;
        else return b;
    }

}