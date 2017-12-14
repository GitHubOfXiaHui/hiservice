package com.bupt.hiservice.blockchain;

import java.io.IOException;
import java.math.BigInteger;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.http.HttpService;

public abstract class BlockchainAPI {

	private static String baseUrl = "http://192.168.1.112:8545/";
	private static Web3j web3 = Web3j.build(new HttpService(baseUrl));

	public static String sendTransaction(String data) throws IOException {
		String createAccount = "0xde41f23e334245b9e28d035e0fb7f700ca332d36";
		Transaction transaction = new Transaction(createAccount, new BigInteger("16000"), new BigInteger("20000000000"),
				new BigInteger("4300000"), null, new BigInteger("0"), data);
		EthSendTransaction trans = web3.ethSendTransaction(transaction).send();
		return trans.getTransactionHash();
	}

	public static String getTransactionDateByHash(String Hash) throws IOException {
		if (Hash != null) {
			EthTransaction ethTransaction = web3.ethGetTransactionByHash(Hash).send();
			return ethTransaction.getResult() == null ? null : ethTransaction.getResult().getInput();
		}
		return null;
	}

}
