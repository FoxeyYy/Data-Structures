template <typename T>
class Queue{
  private:
    typedef struct node{
      T element;
      node *next;
    }NODE;

    NODE *head, *tail;

  public:
    Queue();
    ~Queue();
    T peek();
    T dequeue();
    void enqueue(T e);
    bool empty();
};
