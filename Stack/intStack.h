//struct definition
typedef struct stack{
  int element;
  struct stack *next;
}stack;

//Functions
int empty(stack *s);
int peek(stack *s);
int pop(stack **s);
void push(stack **s, int e);
