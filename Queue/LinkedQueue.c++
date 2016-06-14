#include "Queue.h++"
#include <iostream>

template <class T>
Queue<T> :: Queue(){
  head = tail = NULL;
}

template <class T>
Queue<T> :: ~Queue(){
  while(head){
    NODE *tmp = head;
    head = head->next;
    delete tmp;
  }
}

template <class T>
bool Queue<T> :: empty(){
  return head==NULL ? true : false;
}

template <class T>
T Queue<T> :: peek(){
  return head->element;
}

template <class T>
T Queue<T> :: dequeue(){
  if(empty())
    return T();
  T e = head->element;
  NODE *tmp = head;
  head = head->next;
  delete tmp;
  return e;
}

template <class T>
void Queue<T> :: enqueue(T e){
  NODE *ptr = new NODE;
  ptr->element=e;
  ptr->next=NULL;
  if(empty())
    head=ptr;
  else
    tail->next=ptr;

  tail = ptr;
}

int main(){
    int i;
    Queue<int> s;
    std::cout << "Inserting 1, 2 and 3 to the stack" << std::endl;
    for(i=1;i<=3;i++)
      s.enqueue(i);

    std::cout << "Getting 1st element: " << s.peek() << std::endl;

    std::cout << "popping all elements: ";
    while(!s.empty())
      std::cout << s.dequeue() << " ";
    std::cout << std::endl;
}
