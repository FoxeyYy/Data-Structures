template <class T>

class Stack{
  private:

    typedef struct node{
      T element;
      node *next;
    }NODE;

    NODE *top;

  public:
    Stack();
    ~Stack();
    bool empty();
    T peek();
    T pop();
    void push(T e);
};
