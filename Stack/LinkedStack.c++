#include "Stack.h++"
#include <stddef.h>
#include <iostream>

template <class T>
Stack<T> :: Stack(){
  top = NULL;
}

template <class T>
Stack<T> :: ~Stack(){
  while(top){
    NODE *tmp = top;
    top = top->next;
    delete tmp;
  }
}

template <class T>
bool Stack<T> :: empty(){
  return top==NULL ? true : false;
}

template <class T>
T Stack<T> :: peek(){
  return top->element;
}

template <class T>
T Stack<T> :: pop(){
  T e = top->element;
  NODE *tmp = top;
  top = top->next;
  delete tmp;
  return e;
}

template <class T>
void Stack<T> :: push(T e){
  NODE *ptr = new NODE;
  ptr->element=e;
  ptr->next=top;
  top = ptr;
}

int main(){
    int i;
    Stack<int> s;
    std::cout << "Inserting 1, 2 and 3 to the stack" << std::endl;
    for(i=1;i<=3;i++)
      s.push(i);

    std::cout << "Getting 1st element: " << s.peek() << std::endl;

    std::cout << "popping all elements: ";
    while(!s.empty())
      std::cout << s.pop() << " ";
    std::cout << std::endl;
}
