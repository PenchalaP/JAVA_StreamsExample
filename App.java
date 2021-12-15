package com.datastructures.project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class App {

	// export _JAVA_OPTIONS="-Xmx12g"

	public static void main(String[] args) throws FileNotFoundException {
		int size = Integer.parseInt(args[1]);
		// Question-1 List up all the blocks by their gas used in an increasing
		// order
		// Q-1
		if (args[0].equalsIgnoreCase("1")) {
			System.out.println("Question -1 started ...");
			long heapSize = Runtime.getRuntime().totalMemory();
			System.out.println("Heap Size = " + heapSize);
			String output_dir = "/tmp/pvendip/question_1_output_1.csv";
			List<POJOTotalGas> emps = new ArrayList<POJOTotalGas>();
			ArrayList<String[]> input = new ArrayList<String[]>();
			for (int i = 0; i <= size - 1; i++) {
				int k = i;
				String base = "/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-00000000000";
				if (i >= 10) {
					base = new String(
							"/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-0000000000");
				}
				StringBuilder strFile1 = new StringBuilder(base);
				strFile1 = strFile1.append(k);
				BufferedReader br = new BufferedReader(new FileReader(strFile1.toString()));
				String line = "";
				System.out.println("Start files reading --" + java.time.LocalTime.now());
				int kk = 0;
				try {
					br.readLine();
					while ((line = br.readLine()) != null) {
						POJOTotalGas pojo1 = new POJOTotalGas();
						String[] cols = line.split(",");
						// if (!containsSameTxHash(emps, cols[0])) {
//						pojo1.setTx_hash(cols[0]);
//						if ((cols[3].equals(null)) || (cols[3].isEmpty())) {
//							cols[3] = "0";
//							pojo1.setBlock_number(cols[3]);
//						} else {
//							pojo1.setBlock_number(cols[3]);
//						}
//						pojo1.setBlock_time(cols[5]);
//						if ((cols[7].equals(null)) || (cols[7].isEmpty())) {
//							cols[7] = "0";
//							pojo1.setBlock_gas(Integer.valueOf(cols[7]));
//						} else {
//							pojo1.setBlock_gas(Integer.valueOf(cols[7]));
//						}
//						//emps.add(pojo1);
						

						String[] gg = new String[3];
//						if (cols.length < 2) {
//							continue;
//						}
//						if (!cols[8].chars().allMatch(Character::isDigit)) {
//							continue;
//						}
						gg[0] = cols[1];
						gg[1] = cols[2];
						gg[2] = cols[8];
//						
						input.add(gg);
						kk++;

						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("End files reading --" + java.time.LocalTime.now() + "Total rows:" + kk);
			}
			// List <POJOTotalGas>emps2 =
			// emps.stream().map(POJOTotalGas::getBlock_gas).reduce(0, (value1, value2) ->
			// value1 + value2);

			System.out.println("Start Sort --" + java.time.LocalTime.now());
			Collections.sort(emps);
			System.out.println("SIZE --" + emps.size());
			System.out.println("End Sort --" + java.time.LocalTime.now());

			int k = 0;
			StringBuilder xyz = new StringBuilder("block_number, block_time, block_gas" + "\n");
			try {

				for (POJOTotalGas i : emps) {
					xyz = xyz.append(
							new String(i.getBlock_number() + ',' + i.getBlock_time() + "," + i.getBlock_gas() + "\n"));
					if (k == 2000) {
						break;
					}
					k++;
				}
				FileWriter writer = new FileWriter(output_dir);
				writer.write(xyz.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xyz);
		}

		// Question-2: List up all the blocks by their # transactions in an
		// increasing order

		if (args[0].equalsIgnoreCase("2")) {
			System.out.println("Question -2 started ...");
			long heapSize = Runtime.getRuntime().totalMemory();
			System.out.println("Heap Size = " + heapSize);
			String output_dir = "/tmp/pvendip/question_2_output_2.csv";
			List<POJOTwo> emps = new ArrayList<POJOTwo>();
			for (int i = 0; i <= size - 1; i++) {
				int k = i;
				String base = "/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-00000000000";
				if (i >= 10) {
					base = new String(
							"/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-0000000000");
				}
				StringBuilder strFile1 = new StringBuilder(base);
				strFile1 = strFile1.append(k);
				BufferedReader br = new BufferedReader(new FileReader(strFile1.toString()));
				String line = "";
				System.out.println("Start files reading --" + java.time.LocalTime.now());
				int kk = 1;
				try {
					br.readLine();
					while ((line = br.readLine()) != null) {
						POJOTwo pojo1 = new POJOTwo();
						String[] cols = line.split(",");
						pojo1.setTx_hash(cols[0]);
						if ((cols[3].equals(null)) || (cols[3].isEmpty())) {
							cols[3] = "0";
							pojo1.setBlock_number(cols[3]);
						} else {
							pojo1.setBlock_number(cols[3]);
						}
						pojo1.setBlock_time(cols[5]);
						if ((cols[9].equals(null)) || (cols[9].isEmpty())) {
							cols[9] = "0";
							pojo1.setTransactions(Integer.valueOf(cols[9]));
						} else {
							pojo1.setTransactions(Integer.valueOf(cols[9]));
						}
						emps.add(pojo1);
						kk++;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("End files reading --" + java.time.LocalTime.now() + "Total rows:" + kk);
			}
			System.out.println("Start Group by and Sort --" + java.time.LocalTime.now());

			Map<String, List<POJOTwo>> sorted = emps.stream().sorted()
					.collect(Collectors.groupingBy(POJOTwo::getBlock_number));

			System.out.println("End Group By and  Sort --" + java.time.LocalTime.now());

			int k = 0;
			StringBuilder xyz = new StringBuilder("block_number, block_time, transactions" + "\n");
			try {

				for (Entry<String, List<POJOTwo>> e : sorted.entrySet()) {
					for (POJOTwo e1 : e.getValue()) {

						if (k < 2000) {
							xyz = xyz.append(new String(
									e.getKey() + ',' + e1.getBlock_time() + ',' + e1.getTransactions() + "\n"));
							k++;
						}

					}
				}

				FileWriter writer = new FileWriter(output_dir);
				writer.write(xyz.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xyz);
		}

		// Question-3 ::List up all the transactions by their transaction fee in
		// an increasing order

		if (args[0].equalsIgnoreCase("3")) {
			System.out.println("Question -3 started ...");
			long heapSize = Runtime.getRuntime().totalMemory();
			System.out.println("Heap Size = " + heapSize);
			String output_dir = "/tmp/pvendip/question_3_output_1.csv";
			List<POJOTotalGas> emps = new ArrayList<POJOTotalGas>();
			for (int i = 0; i <= size - 1; i++) {
				int k = i;
				String base = "/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-00000000000";
				if (i >= 10) {
					base = new String(
							"/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-0000000000");
				}
				StringBuilder strFile1 = new StringBuilder(base);
				strFile1 = strFile1.append(k);
				BufferedReader br = new BufferedReader(new FileReader(strFile1.toString()));
				String line = "";
				System.out.println("Start files reading --" + java.time.LocalTime.now());
				int kk = 1;
				try {
					br.readLine();
					while ((line = br.readLine()) != null) {
						POJOTotalGas pojo1 = new POJOTotalGas();
						String[] cols = line.split(",");
						// if (!containsSameTxHash(emps, cols[0])) {
						pojo1.setTx_hash(cols[0]);
						if ((cols[3].equals(null)) || (cols[3].isEmpty())) {
							cols[3] = "0";
							pojo1.setBlock_number(cols[3]);
						} else {
							pojo1.setBlock_number(cols[3]);
						}
						pojo1.setBlock_time(cols[5]);
						if ((cols[10].equals(null)) || (cols[10].isEmpty())) {
							cols[10] = "0";
							pojo1.setBlock_gas(Integer.valueOf(cols[10]));
						} else {
							// Transaction Fee = total gas - gas
							pojo1.setBlock_gas(Integer.valueOf(cols[10]) - Integer.valueOf(cols[7]));
						}
						emps.add(pojo1);
						// } else {

//							for (POJOTotalGas a : emps) {
//								if (a.getTx_hash().equals(cols[0])) {
//									// int i1 = a.getBlock_gas() + Integer.valueOf(cols[7]);
//									a.setBlock_gas(a.getBlock_gas() + Integer.valueOf(cols[7]));
//									// a.setBusinessType(getBusinessType(a.getBusinessCode()));
//								}
//							}
//						}
						kk++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("End files reading --" + java.time.LocalTime.now() + "Total rows:" + kk);
			}
			System.out.println("Start Sort --" + java.time.LocalTime.now());
			Collections.sort(emps);
			System.out.println("End Sort --" + java.time.LocalTime.now());

			int k = 0;
			StringBuilder xyz = new StringBuilder("Tx_has, block_number, block_time, Transaction_fee" + "\n");
			try {

				for (POJOTotalGas i : emps) {
					xyz = xyz.append(new String(i.getTx_hash() + ',' + i.getBlock_number() + ',' + i.getBlock_time()
							+ "," + i.getBlock_gas() + "\n"));
					if (k == 2000) {
						break;
					}
					k++;

				}

				FileWriter writer = new FileWriter(output_dir);
				writer.write(xyz.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xyz);
		}

		// Question-4 :: List up all the transactions per block in an increasing
		// order of gas fees

		if (args[0].equalsIgnoreCase("4")) {
			System.out.println("Question -4 started ...");
			long heapSize = Runtime.getRuntime().totalMemory();
			System.out.println("Heap Size = " + heapSize);
			String output_dir = "/tmp/pvendip/question_4_output_1.csv";
			List<POJOFour> emps = new ArrayList<POJOFour>();
			for (int i = 0; i <= size - 1; i++) {
				int k = i;
				String base = "/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-00000000000";
				if (i >= 10) {
					base = new String(
							"/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-0000000000");
				}
				StringBuilder strFile1 = new StringBuilder(base);
				strFile1 = strFile1.append(k);
				BufferedReader br = new BufferedReader(new FileReader(strFile1.toString()));
				String line = "";
				System.out.println("Start files reading --" + java.time.LocalTime.now());
				int kk = 1;
				try {
					br.readLine();
					while ((line = br.readLine()) != null) {
						POJOFour pojo1 = new POJOFour();
						String[] cols = line.split(",");
						// if (!containsSameTxHash(emps, cols[0])) {
						pojo1.setTx_hash(cols[0]);
						if ((cols[3].equals(null)) || (cols[3].isEmpty())) {
							cols[3] = "0";
							pojo1.setBlock_number(Integer.valueOf(cols[3]));
						} else {
							pojo1.setBlock_number(Integer.valueOf(cols[3]));
						}
						pojo1.setBlock_time(cols[5]);
						if ((cols[8].equals(null)) || (cols[8].isEmpty())) {
							cols[8] = "0";
							pojo1.setGas_price(Long.valueOf(cols[8]));
						} else {
							pojo1.setGas_price(Long.valueOf(cols[8]));
						}
						emps.add(pojo1);

						kk++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("End files reading --" + java.time.LocalTime.now() + "Total rows:" + kk);
			}
			System.out.println("Start grouping and Sorting --" + java.time.LocalTime.now());

			Map<Integer, List<POJOFour>> sorted = emps.stream().sorted()
					.collect(Collectors.groupingBy(POJOFour::getBlock_number));

			System.out.println("End grouping and Sorting by Total gas per block --" + java.time.LocalTime.now());

			int k = 0;
			StringBuilder xyz = new StringBuilder("Tx_hash, gas_price, block_number, block_time" + "\n");
			try {

				for (Entry<Integer, List<POJOFour>> e : sorted.entrySet()) {
					for (POJOFour e1 : e.getValue()) {

						// System.out.println(e.getKey() + " = "+ e1.Out());
						if (k < 2000) {
							xyz = xyz.append(new String(e1.getTx_hash() + ',' + e1.getGas_price() + ',' + e.getKey()
									+ "," + e1.getBlock_time() + "\n"));
							k++;
						}

					}
				}
				FileWriter writer = new FileWriter(output_dir);
				writer.write(xyz.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xyz);
		}

		// Question-5 :: List up all the transactions in groups per contract
		// address in an increasing order of the block#

		if (args[0].equalsIgnoreCase("5")) {
			System.out.println("Question -5 started ...");
			long heapSize = Runtime.getRuntime().totalMemory();
			System.out.println("Heap Size = " + heapSize);
			String output_dir = "/tmp/pvendip/question_5_output_1.csv";
			List<POJOFive> emps = new ArrayList<POJOFive>();
			for (int i = 0; i <= size - 1; i++) {
				int k = i;
				String base = "/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-00000000000";
				if (i >= 10) {
					base = new String(
							"/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-0000000000");
				}
				StringBuilder strFile1 = new StringBuilder(base);
				strFile1 = strFile1.append(k);
				BufferedReader br = new BufferedReader(new FileReader(strFile1.toString()));
				String line = "";
				System.out.println("Start files reading --" + java.time.LocalTime.now());
				int kk = 1;

				try {
					br.readLine();
					while ((line = br.readLine()) != null) {
						POJOFive pojo1 = new POJOFive();
						String[] cols = line.split(",");
						pojo1.setFrom_addr(cols[1]);
						pojo1.setTo_addr(cols[2]);
						pojo1.setTx_hash(cols[0]);

						if ((cols[3].equals(null)) || (cols[3].isEmpty())) {
							cols[3] = "0";
							pojo1.setBlock_number(Integer.valueOf(cols[3]));
						} else {
							pojo1.setBlock_number(Integer.valueOf(cols[3]));
						}
						pojo1.setBlock_time(cols[5]);
						pojo1.setToken_transfers(cols[4]);
						pojo1.setContact_adrs(cols[1] + cols[2]);

						emps.add(pojo1);
						kk++;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("End files reading --" + java.time.LocalTime.now() + "Total rows:" + kk);
			}
			System.out.println("Start Group and Sort --" + java.time.LocalTime.now());

			Map<String, List<POJOFive>> sorted = emps.stream().sorted()
					.collect(Collectors.groupingBy(POJOFive::getFrom_addr));

			System.out.println("End Group and Sort --" + java.time.LocalTime.now());

			int k = 0;
			StringBuilder xyz = new StringBuilder("from_addr, to_addr, tx_hash, block_number, block_time" + "\n");
			try {

				for (Entry<String, List<POJOFive>> e : sorted.entrySet()) {
					for (POJOFive e1 : e.getValue()) {

						if (k < 1000) {
							xyz = xyz.append(new String(e1.getFrom_addr() + ',' + e1.getTo_addr() + ','
									+ e1.getTx_hash() + ',' + e.getKey() + "," + e1.getBlock_time() + "\n"));
							k++;
						}

					}
				}
				FileWriter writer = new FileWriter(output_dir);
				writer.write(xyz.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xyz);
		}

		// Question-6 :: Search for a particular block# and then display its
		// transactions list
		String thirdrdArg = args[2];
		if (args[0].equalsIgnoreCase("6") && (!thirdrdArg.isEmpty())) {
			System.out.println("Question -6 started ...");
			long heapSize = Runtime.getRuntime().totalMemory();
			System.out.println("Heap Size = " + heapSize);
			String output_dir = "/tmp/pvendip/question_6_output_1.csv";
			List<POJOSix> emps = new ArrayList<POJOSix>();
			for (int i = 0; i <= size - 1; i++) {
				int k = i;
				String base = "/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-00000000000";
				if (i >= 10) {
					base = new String(
							"/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-0000000000");
				}
				StringBuilder strFile1 = new StringBuilder(base);
				strFile1 = strFile1.append(k);
				BufferedReader br = new BufferedReader(new FileReader(strFile1.toString()));
				String line = "";
				System.out.println("Start files reading --" + java.time.LocalTime.now());
				int kk = 1;
				try {
					br.readLine();
					// int kk = 1;
					while ((line = br.readLine()) != null) {
						POJOSix pojo1 = new POJOSix();
						String[] cols = line.split(",");
						if (thirdrdArg.equals(cols[3])) {
							pojo1.setTx_hash(cols[0]);
							if ((cols[3].equals(null)) || (cols[3].isEmpty())) {
								cols[3] = "0";
								pojo1.setBlock_number(Integer.valueOf(cols[3]));
							} else {
								pojo1.setBlock_number(Integer.valueOf(cols[3]));
							}
							pojo1.setBlock_time(cols[5]);
							pojo1.setTx_index_in_block(Long.valueOf(cols[9]));
							if ((cols[10].equals(null)) || (cols[10].isEmpty())) {
								cols[10] = "0";
								pojo1.setTransaction_fee(Long.valueOf(cols[10]));
							} else {
								// Transaction Fee = total gas - gas
								pojo1.setTransaction_fee(Long.valueOf(cols[10]) - Long.valueOf(cols[7]));
							}
							emps.add(pojo1);
							kk++;
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("End files reading --" + java.time.LocalTime.now() + "Total rows:" + kk);
			}
			System.out.println("Start Sort --" + java.time.LocalTime.now());
			List<POJOSix> temp = new ArrayList<POJOSix>();
			for (POJOSix x : emps) {
				if (!temp.contains(x)) {
					temp.add(x);
				}
			}
			Collections.sort(temp);
			System.out.println("End Sort --" + java.time.LocalTime.now());

			int k = 0;
			StringBuilder xyz = new StringBuilder(
					"Tx_hash,transaction_fee,tx_index_in_block,block_number,block_time " + "\n");
			try {

				for (int i = temp.size() - 1; i >= 0; i--) {
					xyz = xyz.append(new String(temp.get(i).getTx_hash() + ',' + temp.get(i).getTransaction_fee() + ","
							+ temp.get(i).getTx_index_in_block() + "," + temp.get(i).getBlock_number() + ","
							+ temp.get(i).getBlock_time() + "\n"));

					if (k < 2000) {
						break;
					}
					k++;

				}

				FileWriter writer = new FileWriter(output_dir);
				writer.write(xyz.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xyz);
		}

		// Question-7 :: Search for a particular transaction hash code for an
		// index within a block# and then display transaction fee and block#

		if (args[0].equalsIgnoreCase("7") && (!thirdrdArg.isEmpty())) {
			System.out.println("Question -7 started ...");
			long heapSize = Runtime.getRuntime().totalMemory();
			System.out.println("Heap Size = " + heapSize);
			String output_dir = "/tmp/pvendip/question_7_output_1.csv";
			List<POJOSeven> emps = new ArrayList<POJOSeven>();
			for (int i = 0; i <= size - 1; i++) {
				int k = i;
				String base = "/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-00000000000";
				if (i >= 10) {
					base = new String(
							"/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-0000000000");
				}
				StringBuilder strFile1 = new StringBuilder(base);
				strFile1 = strFile1.append(k);
				BufferedReader br = new BufferedReader(new FileReader(strFile1.toString()));
				String line = "";
				System.out.println("Start files reading --" + java.time.LocalTime.now());
				int kk = 1;
				try {
					br.readLine();
					// int kk = 1;
					while ((line = br.readLine()) != null) {
						POJOSeven pojo1 = new POJOSeven();
						String[] cols = line.split(",");
						if (thirdrdArg.equals(cols[0])) {
							pojo1.setTx_hash(cols[0]);
							if ((cols[3].equals(null)) || (cols[3].isEmpty())) {
								cols[3] = "0";
								pojo1.setBlock_number(Integer.valueOf(cols[3]));
							} else {
								pojo1.setBlock_number(Integer.valueOf(cols[3]));
							}
							pojo1.setBlock_time(cols[5]);
							pojo1.setTx_index_in_block(Long.valueOf(cols[9]));
							if ((cols[10].equals(null)) || (cols[10].isEmpty())) {
								cols[10] = "0";
								pojo1.setTransaction_fee(Long.valueOf(cols[10]));
							} else {
								// Transaction Fee = total gas - gas
								pojo1.setTransaction_fee(Long.valueOf(cols[10]) - Long.valueOf(cols[7]));
							}
							emps.add(pojo1);
							kk++;
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("End files reading --" + java.time.LocalTime.now() + "Total rows:" + kk);
			}
			System.out.println("Start Unique Hash and Sort  --" + java.time.LocalTime.now());
			List<POJOSeven> temp = new ArrayList<POJOSeven>();
			for (POJOSeven x : emps) {
				if (!temp.contains(x)) {
					temp.add(x);
				}
			}
			Collections.sort(temp);
			System.out.println("End search --" + java.time.LocalTime.now());
			int k = 0;
			StringBuilder xyz = new StringBuilder(
					"Tx_hash,transaction_fee,tx_index_in_block,block_number,block_time " + "\n");
			try {

				for (int i = temp.size() - 1; i >= 0; i--) {
					xyz = xyz.append(new String(temp.get(i).getTx_hash() + ',' + temp.get(i).getTransaction_fee() + ","
							+ temp.get(i).getTx_index_in_block() + "," + temp.get(i).getBlock_number() + ","
							+ temp.get(i).getBlock_time() + "\n"));
					if (k == 2000) {
						break;
					}

					k++;

				}

				FileWriter writer = new FileWriter(output_dir);
				writer.write(xyz.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xyz);
		}

		// Question-8:: Identify and list up all the transactions originated
		// from a particular public-key (node) and their total transaction fee
		if (args[0].equalsIgnoreCase("8") && (!thirdrdArg.isEmpty())) {
			System.out.println("Question -8 started ...");
			long heapSize = Runtime.getRuntime().totalMemory();
			System.out.println("Heap Size = " + heapSize);
			String output_dir = "/tmp/pvendip/question_8_output_1.csv";
			List<POJOEight> emps = new ArrayList<POJOEight>();
			for (int i = 0; i <= size - 1; i++) {
				int k = i;
				String base = "/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-00000000000";
				if (i >= 10) {
					base = new String(
							"/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-0000000000");
				}
				StringBuilder strFile1 = new StringBuilder(base);
				strFile1 = strFile1.append(k);
				BufferedReader br = new BufferedReader(new FileReader(strFile1.toString()));
				String line = "";
				System.out.println("Start files reading --" + java.time.LocalTime.now());
				int kk = 1;
				try {
					br.readLine();
					// int kk = 1;
					while ((line = br.readLine()) != null) {
						POJOEight pojo1 = new POJOEight();
						String[] cols = line.split(",");
						if (thirdrdArg.equals(cols[1])) {
							pojo1.setTx_hash(cols[0]);
							if ((cols[3].equals(null)) || (cols[3].isEmpty())) {
								cols[3] = "0";
								pojo1.setBlock_number(Integer.valueOf(cols[3]));
							} else {
								pojo1.setBlock_number(Integer.valueOf(cols[3]));
							}
							pojo1.setBlock_time(cols[5]);
							pojo1.setFrom_adrs(cols[1]);
							if ((cols[10].equals(null)) || (cols[10].isEmpty())) {
								cols[10] = "0";
								pojo1.setTransaction_fee(Long.valueOf(cols[10]));
							} else {
								// Transaction Fee = total gas - gas
								pojo1.setTransaction_fee(Long.valueOf(cols[10]) - Long.valueOf(cols[7]));
							}
							emps.add(pojo1);
							kk++;
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("End files reading --" + java.time.LocalTime.now() + "Total rows:" + kk);
			}
			System.out.println("Start Unique Hash and Sort  --" + java.time.LocalTime.now());
			List<POJOEight> temp = new ArrayList<POJOEight>();
			for (POJOEight x : emps) {
				if (!temp.contains(x)) {
					temp.add(x);
				}
			}
			Collections.sort(temp);
			System.out.println("End search --" + java.time.LocalTime.now());
			int k = 0;
			StringBuilder xyz = new StringBuilder(
					"from_addr, tx_hash, block_number, block_time, transaction_fee " + "\n");

			try {

				for (int i = temp.size() - 1; i >= 0; i--) {
					xyz = xyz.append(new String(temp.get(i).getFrom_adrs() + ',' + temp.get(i).getTx_hash() + ","
							+ temp.get(i).getBlock_time() + "," + temp.get(i).getBlock_number() + ","
							+ temp.get(i).getTransaction_fee() + "\n"));
					if (k == 2000) {
						break;

					}
					k++;
				}
				FileWriter writer = new FileWriter(output_dir);
				writer.write(xyz.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xyz);
		}

		// Question-9 :: Identify and list up all the transactions destined to a
		// particular public-key (node) and their total transaction fee

		if (args[0].equalsIgnoreCase("9") && (!thirdrdArg.isEmpty())) {
			System.out.println("Question -9 started ...");
			long heapSize = Runtime.getRuntime().totalMemory();
			System.out.println("Heap Size = " + heapSize);
			String output_dir = "/tmp/pvendip/question_9_output_1.csv";
			List<POJONine> emps = new ArrayList<POJONine>();
			for (int i = 0; i <= size - 1; i++) {
				int k = i;
				String base = "/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-00000000000";
				if (i >= 10) {
					base = new String(
							"/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-0000000000");
				}
				StringBuilder strFile1 = new StringBuilder(base);
				strFile1 = strFile1.append(k);
				BufferedReader br = new BufferedReader(new FileReader(strFile1.toString()));
				String line = "";
				System.out.println("Start files reading --" + java.time.LocalTime.now());
				int kk = 1;
				try {
					br.readLine();
					// int kk = 1;
					while ((line = br.readLine()) != null) {
						POJONine pojo1 = new POJONine();
						String[] cols = line.split(",");
						// checking to address
						if (thirdrdArg.equals(cols[2])) {
							pojo1.setTx_hash(cols[0]);
							if ((cols[3].equals(null)) || (cols[3].isEmpty())) {
								cols[3] = "0";
								pojo1.setBlock_number(Integer.valueOf(cols[3]));
							} else {
								pojo1.setBlock_number(Integer.valueOf(cols[3]));
							}
							pojo1.setBlock_time(cols[5]);
							pojo1.setTo_addr(cols[2]);
							if ((cols[10].equals(null)) || (cols[10].isEmpty())) {
								cols[10] = "0";
								pojo1.setTransaction_fee(Long.valueOf(cols[10]));
							} else {
								// Transaction Fee = total gas - gas
								pojo1.setTransaction_fee(Long.valueOf(cols[10]) - Long.valueOf(cols[7]));
							}

							emps.add(pojo1);
							kk++;
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("End files reading --" + java.time.LocalTime.now() + "Total rows:" + kk);
			}
			System.out.println("Start Unique Hash and Sort  --" + java.time.LocalTime.now());
			List<POJONine> temp = new ArrayList<POJONine>();
			for (POJONine x : emps) {
				if (!temp.contains(x)) {
					temp.add(x);
				}
			}
			Collections.sort(temp);
			System.out.println("End search --" + java.time.LocalTime.now());
			int k = 0;
			StringBuilder xyz = new StringBuilder(
					"to_addr, tx_hash, block_number, block_time, transaction_fee " + "\n");
			try {

				for (int i = temp.size() - 1; i >= 0; i--) {
					xyz = xyz.append(new String(temp.get(i).getTo_addr() + ',' + temp.get(i).getTx_hash() + ","
							+ temp.get(i).getBlock_time() + "," + temp.get(i).getBlock_number() + ","
							+ temp.get(i).getTransaction_fee() + "\n"));
					if (k == 2000) {
						break;

					}
					k++;
				}

				FileWriter writer = new FileWriter(output_dir);
				writer.write(xyz.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xyz);
		}

		// Question-10 :: Identify the transaction id in a particular node
		// (contract id) with the largest (smallest) value of tokens
		if (args[0].equalsIgnoreCase("10") && (!thirdrdArg.isEmpty())) {
			System.out.println("Question -10 started ...");
			long heapSize = Runtime.getRuntime().totalMemory();
			System.out.println("Heap Size = " + heapSize);
			String output_dir = "/tmp/pvendip/question_10_output_1.csv";
			List<POJOTen> emps = new ArrayList<POJOTen>();
			for (int i = 0; i <= size - 1; i++) {
				int k = i;
				String base = "/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-00000000000";
				if (i >= 10) {
					base = new String(
							"/home/scratch1/cs5413/ethereum/transactions/tx_transfers_10-11-2020_etherum_tx_csv-0000000000");
				}
				StringBuilder strFile1 = new StringBuilder(base);
				strFile1 = strFile1.append(k);
				BufferedReader br = new BufferedReader(new FileReader(strFile1.toString()));
				String line = "";
				System.out.println("Start files reading --" + java.time.LocalTime.now());
				int kk = 1;
				try {
					br.readLine();
					// int kk = 1;
					while ((line = br.readLine()) != null) {
						POJOTen pojo1 = new POJOTen();
						String[] cols = line.split(",");
						if (thirdrdArg.equals(cols[1]) || thirdrdArg.equals(cols[2])) {
							pojo1.setFrom_address(cols[1]);
							pojo1.setFrom_address(cols[2]);
							pojo1.setToken_transfer(new BigInteger(cols[4]));
							emps.add(pojo1);
							kk++;
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("End files reading --" + java.time.LocalTime.now() + "Total rows:" + kk);
			}
			System.out.println("Start finding MAX and MIN--" + java.time.LocalTime.now());

			POJOTen min = emps.stream().collect(Collectors.minBy(Comparator.comparing(POJOTen::getToken_transfer)))
					.get();
			BigInteger min_token_tranfer = min.getToken_transfer();
			POJOTen max = emps.stream().collect(Collectors.maxBy(Comparator.comparing(POJOTen::getToken_transfer)))
					.get();
			BigInteger max_token_tranfer = max.getToken_transfer();
			System.out.println("End finding MAX and MIN --" + java.time.LocalTime.now());

			StringBuilder xyz = new StringBuilder("From_addr/To_Addr, max_token_transfer, min_token_transfer" + "\n");
			xyz = xyz.append(new String(thirdrdArg + ',' + max_token_tranfer + ',' + min_token_tranfer));
			try {
				FileWriter writer = new FileWriter(output_dir);
				writer.write(xyz.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(xyz);
		}

	}

	private static boolean containsSameTxHash(List<POJOTotalGas> emps, String cols) {

		// TODO Auto-generated method stub

		for (POJOTotalGas a : emps) {
			a.getTx_hash().equals(cols);
			return true;
		}
		return false;

	}

}
