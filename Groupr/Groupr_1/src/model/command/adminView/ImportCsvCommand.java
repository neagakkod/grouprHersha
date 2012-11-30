package model.command.adminView;


import java.io.DataInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.role.IRole;
import org.dsrg.soenea.domain.role.RoleFactory;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.UserFactory;
import org.dsrg.soenea.service.registry.MissingResourceException;

import model.PasswordService;
import model.profile.ProfileFactory;

public class ImportCsvCommand extends DomainCommand {
	HttpServletRequest myRequest;
	
	public ImportCsvCommand(Helper helper,HttpServletRequest request) {
        super(helper);
        myRequest=request;
    }
    
    @Override
	public void setUp() throws CommandException {
	}
    
	@Override
	public void process() throws CommandException {
			 
			String contentType =  myRequest.getContentType();
			try{	
				if((!contentType.isEmpty()) && ((contentType.indexOf("multipart/form-data")) >= 0))
				{
				 	DataInputStream in = new DataInputStream(myRequest.getInputStream());
					int formDataLength = myRequest.getContentLength();
					byte dataBytes[] = new byte[formDataLength];
					int byteRead = 0;
					int totalBytesRead = 0;
					
					while (totalBytesRead < formDataLength) {
						byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
						totalBytesRead += byteRead;
					}
					
					String content = new String(dataBytes);
					System.out.println("content=" + content);
					StringTokenizer lineTokenizer = new StringTokenizer(content, "\n");
					boolean startRead = false;
					while(lineTokenizer.hasMoreElements())
					{
						String line = lineTokenizer.nextToken();
						if (line.contains("Content-Type: "))
						{	
							if (!line.contains("csv"))
								throw new CommandException("Wrong file type uploaded!");
							else 
							{
								startRead = true;
								continue;
							}
						}
						if (line.equals("\r") || line.equals("\n"))
							continue;
						if (startRead)
						{
							if (line.contains("--"))
								continue;
							StringTokenizer objectTokenizer = new StringTokenizer(line,",");
							List<IRole> roles = new ArrayList<IRole>();
							try {
								roles.add(RoleFactory.create(2));
							} catch (MissingResourceException e) {
								throw new CommandException(e);
							} catch (ClassNotFoundException e) {
								throw new CommandException(e);
							} catch (InstantiationException e) {
								throw new CommandException(e);
							} catch (IllegalAccessException e) {
								throw new CommandException(e);
							}
							try {
								IUser user = UserFactory.createNew(objectTokenizer.nextToken(), PasswordService.MD5encode(objectTokenizer.nextToken()), roles);
								ProfileFactory.createNew(user, objectTokenizer.nextToken(), objectTokenizer.nextToken());
							} catch (NoSuchElementException e) {
								throw new CommandException("Wrong structure of the CSV file!");
							}
						}
					}
				}
			} catch (IOException e) {
				throw new CommandException(e);
			} catch (SQLException e) {
				throw new CommandException(e);
			} catch (MapperException e) {
				throw new CommandException(e);
			} catch (NoSuchElementException e){	}
	}
	@Override
	public void tearDown() throws CommandError {
	}
}
