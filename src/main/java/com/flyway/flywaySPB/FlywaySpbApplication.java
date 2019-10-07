package com.flyway.flywaySPB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlywaySpbApplication implements CommandLineRunner {
	@Autowired
	@Qualifier("flyways")
	private Flyway flyway;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	boolean x = false;
	String letter = "";
	int i = 0;

	/*
	 * @Autowired private FlywayProperties flywayProperties;
	 * 
	 * @Autowired private FluentConfiguration fluentConfigration;
	 */
	@Bean
	public Flyway flyways(FluentConfigration fluentConfigration) {
		return new Flyway(fluentConfigration);
	}

	public static void main(String[] args) {
		SpringApplication.run(FlywaySpbApplication.class, args).close();

	}

	@Override
	public void run(String... args) {

		do {
			System.out.print("Enter one character (C)lean (I)nfo (M)igrate (R)epair (V)alidate (E)xit : ");
			try {
				letter = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			switch (letter.toUpperCase()) {
			case "I":
				info();
				break;
			case "M":
				migrations();
				break;
			case "R":
				repair();
				break;
			case "V":
				validate();
				break;
			case "C":
				clean();
				break;
			case "E":
				System.out.println("Exit");
				System.exit(0);
				x = true;
				break;
			default:
				System.out.println("Invalid character - must enter one character C,I,E,M,R,V:");
				break;
			}
		} while (x == false);
		// System.out.println(flyway.migrate());
	}

	private void clean() {
		String confirm = "N";
		boolean x = false;
		do {
			System.out.println("WARNING!!! Clean Will do drop your schemas All objects "
					+ "(tables, views, procedures, …) Do you sure?(Y/N): ");
			try {
				confirm = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			switch (confirm.toUpperCase()) {
			case "Y":
				try {
					flyway.clean();
				} catch (FlywayException e) {
					e.printStackTrace();
				}
				x = true;
				break;
			case "N":
				x = true;
				break;
			default:
				break;
			}
		} while (x == false);

	}

	private void validate() {
		try {
			flyway.baseline();
			flyway.repair();
			flyway.validate();
		} catch (FlywayException e) {
			e.printStackTrace();
		}
	}

	private void repair() {
		try {
			flyway.baseline();
			flyway.repair();
		} catch (FlywayException e) {
			e.printStackTrace();
		}
	}

	private void migrations() {
		try {
			flyway.baseline();
			flyway.repair();
			System.out.println("Result: " + flyway.migrate());
		} catch (FlywayException e) {
			e.printStackTrace();
		}
	}

	private void info() {
		flyway.baseline();
		MigrationInfoService migrationInfoService = flyway.info();
		MigrationInfo[] migrationInfo = migrationInfoService.all();
		for (int i = 0; i <= 75; i++) {
			if (i == 75) {
				System.out.println("-");
			} else {
				System.out.print("- ");
			}
		}
		for (MigrationInfo mi : migrationInfo) {
			String version = "Repeatable";
			String InstallOn = "Not Install";
			String ExecutionTime = "Not Execution";
			String InstallBy = "Not Install";
			if (mi.getVersion() != null)
				version = mi.getVersion().toString();
			String state = mi.getState().toString();
			String Description = mi.getDescription().toString();
			String Type = mi.getType().toString();
			if (mi.getInstalledOn() != null)
				InstallOn = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(mi.getInstalledOn());
			if (mi.getExecutionTime() != null)
				ExecutionTime = mi.getExecutionTime().toString() + "ms";
			if (mi.getInstalledBy() != null)
				InstallBy = mi.getInstalledBy().toString();
			System.out.printf("Version: %-20s ||Description: %-45s ||Type: %-14s "
					+ "||InstallOn: %-20s ||\nState: %-22s ||InstallBy: %-47s ||TimeExections: %-39s " + "||\n",
					version, Description, Type, InstallOn, state, InstallBy, ExecutionTime);
			for (int i = 0; i <= 75; i++) {
				if (i == 75) {
					System.out.println("-");
				} else {
					System.out.print("- ");
				}
			}
		}
	}

}
