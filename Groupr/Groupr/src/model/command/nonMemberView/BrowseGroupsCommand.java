package model.command.nonMemberView;

import java.util.ArrayList;

import model.group.IGroup;
import model.group.mapper.GroupInputMapper;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;

public class BrowseGroupsCommand extends DomainCommand {
	public BrowseGroupsCommand(Helper helper) {
        super(helper);
    }
	
    @Override
	public void setUp() throws CommandException {
	}

	
	@Override
	public void process() throws CommandException {
    	try {	
    		// get page attribute
    		int page = helper.getInt("page");
    		int pageCount  = (int) Math.ceil( GroupInputMapper.findCount() / 5.0 );
    		
    		ArrayList<IGroup> groups = GroupInputMapper.findPage(page);
    		
    		helper.setRequestAttribute("groups", groups);//find all groups
    		helper.setRequestAttribute("pageCount", pageCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void tearDown() throws CommandError {
	}
}
