#include "intStack.h"
#include <stdlib.h>
#include <stdio.h>

#define TRUE 1
#define FALSE 0

int empty(stack *s){
  if(s->next==NULL)
    return TRUE;
  return FALSE;
}

int peek(stack *s){
  return s->element;
}

int pop(stack **s){
  int e = (*s)->element;
  stack *aux = *s;
  *s = (*s)->next;
  free(aux);
  return e;
}

void push(stack **s, int e){
  stack *top = malloc(sizeof(stack));
  top->element=e;
  top->next = *s;
  *s = top;
}

void main(){
  int i;
  stack *s = malloc(sizeof(stack));
  printf("Inserting 1, 2 and 3 to the stack\n");
  for(i=1;i<=3;i++)
    push(&s,i);

  printf("Getting 1st element: %d\n", peek(s));

  printf("popping all elements: ");
  while(!empty(s))
    printf("%d ", pop(&s));
  printf("\n");

}
