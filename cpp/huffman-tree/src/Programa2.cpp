#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct bits
{
    unsigned char b7:1;
    unsigned char b6:1;
    unsigned char b5:1;
    unsigned char b4:1;
    unsigned char b3:1;
    unsigned char b2:1;
    unsigned char b1:1;
    unsigned char b0:1;
};

struct lista{
    int frequencia, simbolo;
    char palavra[15];
    char codigo[30];
    struct lista *prox;
};
typedef struct lista Lista;

struct tree{
    int frequencia, simbolo;
    struct tree *dir, *esq;
};
typedef struct tree Tree;


void carregarTabela(Lista **lista){
    FILE *arq = fopen("TabelaDeRegistros.dat", "rb");
    Lista *aux, reg, *p;
    if(arq != NULL){
        fread(&reg, sizeof(Lista), 1 ,arq);
        while(!feof(arq)){
            aux = (Lista*)malloc(sizeof(Lista));
            aux->frequencia = reg.frequencia;
            aux->simbolo = reg.simbolo;
            strcpy(aux->palavra, reg.palavra);
            strcpy(aux->codigo, reg.codigo);
            aux->prox = NULL;

            if(*lista == NULL){
                *lista = aux;
                p = *lista;
            }
            else{
                p->prox = aux;
                p = p->prox;
            }
            fread(&reg, sizeof(Lista), 1 ,arq);
        }
        fclose(arq);
    }
}

void criarArvore(Tree **raiz, Lista *lista){
    Lista *reg;
    Tree *aux, *p;
    char cod[30];
    int i;
    aux = (Tree*)malloc(sizeof(Tree));
    aux->dir = aux->esq = NULL;
    aux->simbolo = -1;
    *raiz = aux;
    while(lista != NULL){
        i = 0;
        reg = lista;
        strcpy(cod, reg->codigo);
        aux = *raiz;
        while(cod[i] != '\0'){
            if(cod[i] == '1'){
                if(aux->dir == NULL){
                    p = (Tree*)malloc(sizeof(Tree));
                    p->dir = p->esq = NULL;
                    if(cod[i+1] == '\0')
                        p->simbolo = reg->simbolo;
                    else
                        p->simbolo = -1;
                    aux->dir = p;
                }
                aux = aux->dir;
            }
            else if(cod[i] == '0'){
                if(aux->esq == NULL){
                    p = (Tree*)malloc(sizeof(Tree));
                    p->dir = p->esq = NULL;
                    if(cod[i+1] == '\0')
                        p->simbolo = reg->simbolo;
                    else
                        p->simbolo = -1;
                    aux->esq = p;
                }
                aux = aux->esq;
            }
            i++;
        }
        lista = lista->prox;
    }
}

void buscaPalavra(Lista *lista, int simboloAlvo, char palavra[15]){
    int encontrou = 0;
    while(lista != NULL && encontrou == 0){
        if(lista->simbolo == simboloAlvo){
            strcpy(palavra, lista->palavra);
            encontrou = 1;
        }
        lista = lista->prox;
    }
    if(encontrou == 0){
        strcpy(palavra, "");
    }
}

void decodificaPalavra(Tree *raiz, Lista *lista, unsigned char vet[5000], int totalBits){
    Tree *aux = raiz;
    char frase[5000], palavra[15];
    frase[0] = '\0';
    int i;
    printf("\nDecodificando...\n");
    for(i = 0; i < totalBits; i++){
        if(vet[i] == 1){
            if(aux->dir != NULL) aux = aux->dir;
        }
        else{
            if(aux->esq != NULL) aux = aux->esq;
        }
        if(aux->simbolo != -1){
            buscaPalavra(lista, aux->simbolo, palavra);
            strcat(frase, palavra);
            aux = raiz;
        }
    }
    printf("\nFrase Final: %s\n", frase);
}


int main(){
    unsigned char vet[5000];
    Lista *lista = NULL;
    Tree *raiz = NULL;
    FILE *arq = fopen("PalavraCodificada.dat", "rb");
    struct bits uval;
    int i = 0;
    fread(&uval, sizeof(unsigned char), 1, arq);
    while(!feof(arq)){
        vet[i++] = uval.b0;
        vet[i++] = uval.b1;
        vet[i++] = uval.b2;
        vet[i++] = uval.b3;
        vet[i++] = uval.b4;
        vet[i++] = uval.b5;
        vet[i++] = uval.b6;
        vet[i++] = uval.b7;

        fread(&uval, sizeof(unsigned char), 1, arq);
    }
    fclose(arq);

    int totalBits = i;
    printf("Total de Bits Lidos: %d\n", totalBits);
    for (i=0; i<totalBits; i++)
        printf("%d", vet[i]);
    carregarTabela(&lista);
    criarArvore(&raiz, lista);
    decodificaPalavra(raiz, lista, vet, totalBits);


    return 0;
}
