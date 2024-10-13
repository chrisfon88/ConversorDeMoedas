# 📚 Sobre o Projeto

Este projeto de trata de aplicativo simples em Java que permite converter valores entre diferentes moedas, utilizando a API ExchangeRate para obter as taxas de conversão atuais.
O mesmo foi realizado em virtude de uma Challenge do curso **Praticando Java: Challenge conversor de moedas** que faz parte da formação ONE (Oracle Next Education) + Alura.

## ⚙️ Funcionalidades

- Conversão de valores entre diversas moedas: USD, EUR, BRL, JPY, GBP, AUD.
- Verificação de validade das moedas escolhidas.
- Validação do valor a ser convertido (somente números positivos).
- Exibição da taxa de conversão e do resultado da conversão.

## 🚀 Tecnologias Utilizadas

- **Java**: Linguagem de programação principal do projeto.
- **HttpClient**: Para realizar as requisições HTTP à API de conversão de moedas.
- **Gson**: Biblioteca para manipulação de JSON, usada para processar a resposta da API.
- **API ExchangeRate**: Serviço externo usado para obter as taxas de câmbio em tempo real.

## 🛠️ Como Usar

Para fazer com que o códio funcione é necessário obter uma chave de acesso para a API ExchangeRate-API.

- Acesse a página https://www.exchangerate-api.com//e cadastre o seu e-mail para obter uma chave gratuitamente.
- Após o recebimento da chave em seu e-mail cole ela no lugar do código java que está na classe principal "ConversorDeMoedas".

**String apiKey = "sua-chave-de-api";**

- Depois de ter inserido a chave basta seguir as instruções no terminal para selecionar as moedas e inserir o valor a ser convertido.

## 📋 Pré-requisitos
- Java Development Kit (JDK) 17 ou superior.
- Dependência da biblioteca Gson (disponível neste repositório: https://mvnrepository.com/artifact/com.google.code.gson/gson).

## 🗂️ Estrutura do Código
- **ConversorDeMoedas**: Classe principal que contém a lógica de requisição à API e processamento da conversão.
- **Conversao**: Classe modelo para representar os dados de resposta da API.