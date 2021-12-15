/*package com.datastructures.project1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class JServerAuthUtility {

	public static String server = "csx8.cs.okstate.edu";
	public static String userName = "pvendip";
	public static String password = "greatBu=falo59";
	public static String path = "/home/scratch1/cs5413/ethereum/transactions";
	public static String fileName = "tx_transfers_10-11-2020_etherum_tx_csv-000000000001";

	public static void main(String[] args) throws Exception {
		String hostname = server;
		String username = userName;
		JSch jsch = new JSch();
		Session session = null;
		System.out.println("Trying to connect.....");
		try {
			session = jsch.getSession(username, hostname, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			session.connect();
			com.jcraft.jsch.Channel channel = session.openChannel("sftp");
			channel.connect();
			System.out.println("connected.....");
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			sftpChannel.cd(path);
			sftpChannel.get(fileName);
			String row = "";
			String[] data = null;
			BufferedReader csvReader = new BufferedReader(new InputStreamReader(sftpChannel.get(fileName)));
			while ((row = csvReader.readLine()) != null) {
				data = row.split(",");				
			}
			System.out.println("Str ... " + data[1]);
			csvReader.close();

			// sftpChannel.get(copyFrom, copyTo);
			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
		System.out.println("Done !!");
	}

}
*/