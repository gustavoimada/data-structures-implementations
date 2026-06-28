#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <conio2.h>
#include <conio.h>


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
typedef struct bits Byte;

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

struct forest {
    Tree *tree;
    struct forest *prox;
}; typedef struct forest Forest;

#include "pilha.h"

Lista *criaNO(char info[15]){
    Lista *caixa = (Lista*)malloc(sizeof(Lista));
    caixa->frequencia = 1;
    caixa->prox = NULL;
    strcpy(caixa->palavra, info);
    return caixa;
}

void exibeLista(Lista *l){
    while(l!= NULL){
        printf("Palavra:%s - Frenquencia:%d - Simbolo:%d\n", l->palavra, l->frequencia, l->simbolo);
        l = l->prox;
    }
}

void exibeFloresta(Forest *f){
    while(f!= NULL){
        printf("| Simbolo:%d - Frenquencia:%d |\n", f->tree->simbolo,f->tree->frequencia);
        f = f->prox;
    }
}

void inserir(Lista **lista, char info[15]){
    Lista *p = *lista, *aux, *ant;
    if(*lista == NULL){
        *lista = criaNO(info);
    }
    else{
        if(strcmp((*lista)->palavra, info) == 0){
            (*lista)->frequencia++;
        }
        else{
            if(strcmp(p->palavra, info) > 0){
                p = criaNO(info);
                p->prox = *lista;
                *lista = p;
            }
            else{
                p = (*lista)->prox;
                ant = *lista;
                while(p!=NULL && strcmp(p->palavra, info) < 0){
                    ant = p;
                    p=p->prox;
                }
                if(p!= NULL && strcmp(p->palavra, info) == 0){
                    p->frequencia++;
                }
                else{
                    aux = criaNO(info);
                    ant->prox = aux;
                    aux->prox = p;
                }
            }
        }
    }
}

void leitura(Lista **lista, char frase[5000]){
    char palavra[15], char_atual;
    int i = 0, j = 0;
    palavra[0] = '\0';
    while(frase[i] != '\0'){
        char_atual = frase[i];
        if(char_atual == ' ' || char_atual == '\n'){
            if(j > 0){
                palavra[j] = '\0';
                inserir(lista, palavra);
                j = 0;
                palavra[0] = '\0';
            }
            if(char_atual == ' '){
                palavra[j=0] = char_atual;
                palavra[++j] = '\0';
                inserir(lista, palavra);
                j = 0;
                palavra[0] = '\0';
            }
        }
        else
            if(j < 14)
                palavra[j++] = char_atual;
        i++;
    }
    if(j > 0){
        palavra[j] = '\0';
        inserir(lista, palavra);
    }
}


void ordenaFrequencia(Lista **lista) {
    Lista *p, *aux, *ant;
    int maior;
    int temp_freq;
    char temp_palavra[15];
    p = *lista;
    while (p != NULL) {
        aux = p;
        maior = p->frequencia;
        ant = p->prox;
        while (ant != NULL) {
            if (ant->frequencia > maior) {
                aux = ant;
                maior = ant->frequencia;
            }
            ant = ant->prox;
        }
        if(aux != p) {
            temp_freq = p->frequencia;
            p->frequencia = aux->frequencia;
            aux->frequencia = temp_freq;
            strcpy(temp_palavra, p->palavra);
            strcpy(p->palavra, aux->palavra);
            strcpy(aux->palavra, temp_palavra);
        }
        p = p->prox;
    }
    int simbolo = 0;
    p = *lista;
    while(p!=NULL){
        p->simbolo = simbolo++;
        p = p->prox;
    }
}

void ordenaFrequenciaFloresta(Forest **floresta){
    Forest *p, *aux, *ant;
    int maior;
    Tree *temp_tree;
    p = *floresta;
    while(p != NULL){
        aux = p;
        maior = p->tree->frequencia;
        ant = p->prox;
        while(ant != NULL){
            if(ant->tree->frequencia > maior){
                aux = ant;
                maior = ant->tree->frequencia;
            }
            ant = ant->prox;
        }
        if(aux != p){
            temp_tree = p->tree;
            p->tree = aux->tree;
            aux->tree = temp_tree;
        }
        p = p->prox;
    }
}

int contaNo(Forest *floresta){
    int contador = 0;
    while(floresta != NULL){
        contador++;
        floresta = floresta->prox;
    }
    return contador;
}

Forest* encontraERemoveMenor(Forest **floresta){
    if(*floresta != NULL){
        Forest *menor = *floresta;
        Forest *prevMenor = NULL;
        Forest *atual = (*floresta)->prox;
        Forest *prev = *floresta;
        while(atual != NULL){
            if(atual->tree->frequencia < menor->tree->frequencia){
                menor = atual;
                prevMenor = prev;
            }
            prev = atual;
            atual = atual->prox;
        }
        if(prevMenor == NULL){
            *floresta = menor->prox;
        }
        else{
            prevMenor->prox = menor->prox;
        }
        menor->prox = NULL;
        return menor;
    }
    else
        return NULL;
}

void criaArvoreHuffman(Forest **floresta, Tree **raiz){
    Forest *no1, *no2, *novoNo;
    Tree *novoNoInterno;
    while(contaNo(*floresta) > 1){
        no1 = encontraERemoveMenor(floresta);
        no2 = encontraERemoveMenor(floresta);
        if(no1 != NULL && no2 != NULL){
            novoNoInterno = (Tree*)malloc(sizeof(Tree));
            novoNoInterno->frequencia = no1->tree->frequencia + no2->tree->frequencia;
            novoNoInterno->simbolo = -1;
            novoNoInterno->esq = no1->tree;
            novoNoInterno->dir = no2->tree;

            novoNo = (Forest*)malloc(sizeof(Forest));
            novoNo->tree = novoNoInterno;

            novoNo->prox = *floresta;
            *floresta = novoNo;

            free(no1);
            free(no2);
            ordenaFrequenciaFloresta(&*floresta);
        }
    }
    if(*floresta != NULL){
        *raiz = (*floresta)->tree;
        free(*floresta);
        *floresta = NULL;
    }
    else{
        *raiz = NULL;
    }
}

void criaHuffman(Lista **lista, Forest **floresta){
    Forest *florestaAux;
    Forest *no;
    Lista *listaAux = *lista;
    while(listaAux!=NULL){
        Tree *folha = (Tree*)malloc(sizeof(Tree));
        folha->frequencia = listaAux->frequencia;
        folha->simbolo = listaAux->simbolo;
        folha->dir = folha->esq = NULL;

        no = (Forest*)malloc(sizeof(Forest));
        if(*floresta == NULL){
            no->tree = folha;
            no->prox = NULL;
            *floresta = no;
        }
        else{
            florestaAux = *floresta;
            while(florestaAux->prox != NULL)
                florestaAux = florestaAux->prox;
            no->tree = folha;
            no->prox = NULL;
            florestaAux->prox = no;
        }
        listaAux = listaAux->prox;
    }
}

void exibeRecursivo(Tree *raiz, int nivel){
    if(raiz != NULL){
        int i;
        exibeRecursivo(raiz->dir, nivel + 1);
        if (raiz->dir != NULL) {
            for(i = 0; i < nivel + 1; i++)
                printf("    ");
            printf("/\n");
        }
        for(i = 0; i < nivel; i++)
            printf("    ");
        if(raiz->simbolo == -1)
            printf("(%d)\n", raiz->frequencia);
         else
            printf("(%d, S:%d)\n", raiz->frequencia, raiz->simbolo);
        if(raiz->esq != NULL) {
            for(i = 0; i < nivel + 1; i++)
                printf("    ");
            printf("\\\n");
        }
        exibeRecursivo(raiz->esq, nivel + 1);
    }
}

Lista *procuraSimbolo(Lista *lista, int simbolo) {
    Lista *valor = NULL;
    int valorDeRetorno = -1;
    int encontrou = 0;
    Lista *aux = lista;
    while(lista != NULL && encontrou == 0) {
        if(lista->simbolo == simbolo){
            valor = lista;
            encontrou = 1;
        }
        else{
            lista = lista->prox;
        }
    }
    return valor;
}

void codigoHuffman(Lista *lista, Tree *raiz){
    if(raiz != NULL) {
        Pilha *pilha = NULL;
        char cAtual[100];
        if(raiz->simbolo != -1){
            Lista *registro = procuraSimbolo(lista, raiz->simbolo);
            if(registro != NULL)
                registro->codigo[0] = '\0';
        }
        else{
            push(&pilha, raiz, "");
            while(pilha != NULL){
                Tree *atual = pop(&pilha, cAtual);
                if(atual->simbolo != -1){
                    Lista *reg = procuraSimbolo(lista, atual->simbolo);
                    if(reg != NULL) strcpy(reg->codigo, cAtual);
                }
                if(atual->dir != NULL){
                    char cNovo[100];
                    strcpy(cNovo, cAtual);
                    strcat(cNovo, "1");
                    push(&pilha, atual->dir, cNovo);
                }
                if(atual->esq != NULL){
                    char cNovo[100];
                    strcpy(cNovo, cAtual);
                    strcat(cNovo, "0");
                    push(&pilha, atual->esq, cNovo);
                }
            }
        }
    }
}

void gravarTabela(Lista *lista, FILE *ptrArqB, Tree *raiz){
    Lista *atual = lista;
    codigoHuffman(lista, raiz);
    printf("\nGravando tabela de registros\n");
    while(atual != NULL){
        fwrite(atual, sizeof(Lista), 1, ptrArqB);
        atual = atual->prox;
    }
    printf("Gravacao concluida.\n");
}

void leTabela(Lista *lista){
    Lista aux;
    FILE *arq = fopen("TabelaDeRegistros.dat", "rb");
    printf("Palavra -- Simbolo -- Frequencia -- Codigo Huffman\n");
    fread(&aux, sizeof(Lista),1, arq);
    while(!feof(arq)){
        if(strcmp(aux.palavra, " ") == 0)
            printf("[ESPACO]  --    %d    --    %d   --    %s\n", aux.simbolo, aux.frequencia, aux.codigo);
        else
            if(strcmp(aux.palavra, "\n") == 0)
                printf("[Pula Linha]  --    %d    --    %d   --    %s\n", aux.simbolo, aux.frequencia, aux.codigo);
            else
                printf("%s  --    %d    --    %d   --    %s\n", aux.palavra, aux.simbolo, aux.frequencia, aux.codigo);
        fread(&aux, sizeof(Lista),1, arq);
    }
    fclose(arq);
}

void procuraCodigoPorPalavra(Lista *lista, char palavra[15], char codigoEncontrado[100]) {
    int encontrou = 0;
    codigoEncontrado[0] = '\0';

    while(lista != NULL && encontrou == 0){
        if(strcmp(lista->palavra, palavra) == 0){
            strcpy(codigoEncontrado, lista->codigo);
            encontrou = 1;
        }
        else{
            lista = lista->prox;
        }
    }
}
void gravaCodigo(Tree *raiz, FILE *ptrArqP, char palavra[15], FILE *ptrArqB, char codigo[1000]){
    Lista aux;
    rewind(ptrArqB);
    fread(&aux, sizeof(Lista), 1 , ptrArqB);
    while(!feof(ptrArqB) && strcmp(aux.palavra,palavra) != 0){
        fread(&aux, sizeof(Lista), 1 , ptrArqB);
    }
    if(strcmp(aux.palavra, palavra) == 0)
        strcat(codigo, aux.codigo);
}

void LePalavra(Tree *raiz, Lista *tab, FILE *arq){

    FILE *ptrArqP = fopen("PalavraCodificada.dat", "wb");
    char frase[5000], codigo[1000];
    codigo[0] = '\0';

    fgets(frase, sizeof(frase), arq);
    fclose(arq);

    char palavra[15];
    int i_frase = 0, j_palavra = 0;
    palavra[0] = '\0';

    char codigoEncontrado[100];
    while(frase[i_frase] != '\0'){
        char char_atual = frase[i_frase];
        if(char_atual == ' '){
            if(j_palavra > 0){
                palavra[j_palavra] = '\0';
                procuraCodigoPorPalavra(tab, palavra, codigoEncontrado);
                if(codigoEncontrado[0] != '\0')
                    strcat(codigo, codigoEncontrado);
                j_palavra = 0;
                palavra[0] = '\0';
            }
            palavra[j_palavra=0] = char_atual;
            palavra[++j_palavra] = '\0';
            procuraCodigoPorPalavra(tab, palavra, codigoEncontrado);
            if (codigoEncontrado[0] != '\0')
                strcat(codigo, codigoEncontrado);
            j_palavra = 0;
            palavra[0] = '\0';
        }
        else
            if(j_palavra < 14)
                palavra[j_palavra++] = char_atual;
        i_frase++;
    }
    if(j_palavra > 0){
        palavra[j_palavra] = '\0';
        procuraCodigoPorPalavra(tab, palavra, codigoEncontrado);
        if (codigoEncontrado[0] != '\0')
            strcat(codigo, codigoEncontrado);
    }

    Byte uval;
    int i_codigo = 0;
    int len = strlen(codigo);
    int contadorBits = 0;
    while(i_codigo <= len - 8){
        uval.b0 = (codigo[i_codigo++] - '0');
        uval.b1 = (codigo[i_codigo++] - '0');
        uval.b2 = (codigo[i_codigo++] - '0');
        uval.b3 = (codigo[i_codigo++] - '0');
        uval.b4 = (codigo[i_codigo++] - '0');
        uval.b5 = (codigo[i_codigo++] - '0');
        uval.b6 = (codigo[i_codigo++] - '0');
        uval.b7 = (codigo[i_codigo++] - '0');
        fwrite(&uval, sizeof(unsigned char), 1, ptrArqP);
    }
    contadorBits = 0;
    while(i_codigo < len){
        int bit = (codigo[i_codigo++] - '0');
        if(contadorBits == 0) uval.b0 = bit;
        else if(contadorBits == 1) uval.b1 = bit;
        else if(contadorBits == 2) uval.b2 = bit;
        else if(contadorBits == 3) uval.b3 = bit;
        else if(contadorBits == 4) uval.b4 = bit;
        else if(contadorBits == 5) uval.b5 = bit;
        else if(contadorBits == 6) uval.b6 = bit;
        contadorBits++;
    }
    if(contadorBits > 0){
        while(contadorBits < 8){
            if(contadorBits == 1) uval.b1 = 0;
            else if(contadorBits == 2) uval.b2 = 0;
            else if(contadorBits == 3) uval.b3 = 0;
            else if(contadorBits == 4) uval.b4 = 0;
            else if(contadorBits == 5) uval.b5 = 0;
            else if(contadorBits == 6) uval.b6 = 0;
            else if(contadorBits == 7) uval.b7 = 0;
            contadorBits++;
        }
        fwrite(&uval, sizeof(unsigned char), 1, ptrArqP);
    }
    fclose(ptrArqP);
}

int main(){
    Forest *floresta = NULL;
    Tree *raiz = NULL;
    Lista *ListaE = NULL;
    FILE *ptrarq = fopen("texto.txt", "r");
    FILE *arq = fopen("frase2.txt", "r");
    FILE *ptrArqB = fopen("TabelaDeRegistros.dat", "wb");
    char frase[5000];
    fgets(frase, sizeof(frase), ptrarq);
    fclose(ptrarq);
    leitura(&ListaE, frase);
    ordenaFrequencia(&ListaE);
    criaHuffman(&ListaE, &floresta);
    criaArvoreHuffman(&floresta, &raiz);
    printf("\n--- Arvore de Huffman ---\n");
    exibeRecursivo(raiz, 0);
//---------------------- Arvore ja criada ----------------//
    gravarTabela(ListaE, ptrArqB, raiz);
    fclose(ptrArqB);
    printf("\n--- Tabela de Codigos ---\n");
    leTabela(ListaE);
    LePalavra(raiz, ListaE, arq);

    printf("\nPrograma finalizado.\n");
    return 0;
}
