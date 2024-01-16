#include<iostream>
#include<vector>

using namespace std;

void swap(int &a, int &b) {
    int t;
    t=a;
    a=b;
    b=t;
}

class Heap {
    int *p;
    int length;

    void bubbleUpHeapify(int i) {
        if(i/2==0)  return; // root
        else if (p[i/2]>p[i])   return; // already heap
        else {
            swap(p[i/2],p[i]);
            bubbleUpHeapify(i/2);
        }
    }

    void bubbleDownHeapify(int i) {
        if(i*2>length)    return; // leaf
        else {
            int left = 2*i;
            int right = 2*i+1;
            int larger = i;

            if(p[left]>p[i])    larger = left;
            if((right<=length) && (p[right]>p[larger])) larger = right;
            if(larger!=i)  {
                swap(p[i],p[larger]);
                bubbleDownHeapify(larger);
            }
        }
    }

public:

    Heap(int sz) {
        p = new int[sz];
        length = 0;
    }

    void insert(int value) {
        p[++length] = value;
        bubbleUpHeapify(length);
    }

    void deleteKey() {
        p[1] = p[length--];
        bubbleDownHeapify(1);
    }

    int size() { return length; }

    int getMax() { return p[1]; }

    friend void heapsort(vector<int>&v);

    ~Heap() { delete []p;}

};

void heapsort(vector<int>&v) {
    Heap heap(v.size());
    for(int i=0; i<v.size(); i++)   heap.insert(v[i]);
    for(int i=v.size(); i>=1; i--) {
        v[v.size()-i] = heap.p[1];
        swap(heap.p[1], heap.p[i]);
        heap.length--;
        heap.bubbleDownHeapify(1);
    }

}

