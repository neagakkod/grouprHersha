package model.command;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;

public class LogoutCommand extends DomainCommand {
	public LogoutCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void setUp() throws CommandException {
	}

	@Override
    public void process() throws CommandException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void tearDown() throws CommandError {
	}
}
