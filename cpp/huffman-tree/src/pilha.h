struct pilha {
    Tree *no;
    char cod[100];
    struct pilha *prox;
}; typedef struct pilha Pilha;

void push(Pilha **topo, Tree *no, char cod[]) {
    Pilha *novo = (Pilha*)malloc(sizeof(Pilha));
    novo->no = no;
    strcpy(novo->cod, cod);
    novo->prox = *topo;
    *topo = novo;
}

Tree* pop(Pilha **topo, char cod[]) {
    if(*topo != NULL){
        Pilha *no = *topo;
        Tree *ret = no->no;
        strcpy(cod, no->cod);
        *topo = no->prox;
        free(no);
        return ret;
    } return NULL;
}
