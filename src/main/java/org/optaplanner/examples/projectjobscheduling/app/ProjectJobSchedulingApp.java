/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.examples.projectjobscheduling.app;

import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.io.File;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.examples.common.app.CommonApp;
import org.optaplanner.examples.common.business.SolutionBusiness;
import org.optaplanner.examples.common.persistence.AbstractSolutionImporter;
import org.optaplanner.examples.common.persistence.AbstractSolutionExporter;
import org.optaplanner.examples.projectjobscheduling.domain.Schedule;
import org.optaplanner.examples.projectjobscheduling.persistence.ProjectJobSchedulingImporter;
import org.optaplanner.examples.projectjobscheduling.persistence.ProjectJobSchedulingXmlImporter;
import org.optaplanner.examples.projectjobscheduling.persistence.ProjectJobSchedulingXmlSolutionFileIO;
import org.optaplanner.examples.projectjobscheduling.persistence.ProjectJobSchedulingXmlExporter;
import org.optaplanner.examples.projectjobscheduling.swingui.ProjectJobSchedulingPanel;
import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;
import org.apache.commons.io.FilenameUtils;
//import org.optaplanner.core.impl.solver.*;
import org.optaplanner.core.config.solver.termination.TerminationConfig;
import org.optaplanner.core.config.solver.SolverConfig;

public class ProjectJobSchedulingApp extends CommonApp<Schedule> {

    public static final String SOLVER_CONFIG =
            "org/optaplanner/examples/projectjobscheduling/projectJobSchedulingSolverConfig.xml";
    public static final String SOLVER_CONFIG2 =
            "org/optaplanner/examples/projectjobscheduling/projectJobSchedulingSolverConfig2.xml";            

    public static final String DATA_DIR_NAME = "projectjobscheduling";

    public static void main(String[] args) {
        prepareSwingEnvironment();
        //new ProjectJobSchedulingApp().init();

        String parf = null;		// имя файла
        String part = null;     // termination опция
        try {
            parf = args[0];
            part = args[1];
        }
        catch(Exception e){
            // if any error occurs
            e.printStackTrace();
        } 
        //String pSConf = SOLVER_CONFIG;
        ProjectJobSchedulingApp app;
        app = new ProjectJobSchedulingApp(); //(pSConf);
        if (part == null) part = "TS60;US10;";
        app.setTermination(part);
        //if (pard == null) pard = "D:\\Workspace_PS\\PS\\data\\projectjobscheduling\\import\\";
        //app.setProcessDir(pard);

        app.init();             
        app.run(parf);   
        System.exit(0);           
    }

    public ProjectJobSchedulingApp() {
        super("Project job scheduling",
                "Official competition name:" +
                        " multi-mode resource-constrained multi-project scheduling problem (MRCMPSP)\n\n" +
                        "Schedule all jobs in time and execution mode.\n\n" +
                        "Minimize project delays.",
                SOLVER_CONFIG, DATA_DIR_NAME,
                ProjectJobSchedulingPanel.LOGO_PATH);
    }

    @Override
    protected ProjectJobSchedulingPanel createSolutionPanel() {
        return new ProjectJobSchedulingPanel();
    }

    @Override
    public SolutionFileIO<Schedule> createSolutionFileIO() {
        return new ProjectJobSchedulingXmlSolutionFileIO();
    }

    @Override
    protected AbstractSolutionImporter[] createSolutionImporters() {
        return new AbstractSolutionImporter[] {
            new ProjectJobSchedulingXmlImporter(),    
            new ProjectJobSchedulingImporter()                
        };
    }

    @Override    
    protected Set<AbstractSolutionExporter> createSolutionExporters() { 
        Set<AbstractSolutionExporter> exporters = new HashSet<>(1);
        exporters.add(new ProjectJobSchedulingXmlExporter());
        return exporters;

    }
    
    public void setTermination(String pTerm) {
    	String[] st = pTerm.split(";");
    	String sval;
    	int ival;
    	for (int i = 0; i < st.length; i++) {
    		sval = st[i];
    		if (sval.startsWith("TS")) {
    			sval = sval.substring(2, sval.length());
    			ival = Integer.parseInt(sval);
    			secondsSpentLimit = ival;
    		}
    	    if (sval.startsWith("US")) {
    	    	sval = sval.substring(2, sval.length());
    	    	ival = Integer.parseInt(sval);
    	    	unimprovedSecondsSpentLimit = ival;
			}    	
    	    if (sval.startsWith("UC")) {
    	    	sval = sval.substring(2, sval.length());
    	    	ival = Integer.parseInt(sval);
    	    	unimprovedStepCountLimit = ival;
			}   	    
    	}
        
    } 

    public void run(String pFile) {
        File f = null;
        File fres = null;
        String p2 = null;
//        File[] paths;
        
        try{    
        	if (pFile == null) pFile = "d:\\Workspace_PSS\\PSS\\data\\projectjobscheduling\\import\\P1570C24.xml"; //test_57457_small.xml"; //"d:\\Workspace_VR\\P1568C1-n374-k93-z375-b0-s0.vrp"; 
        	f = new File(pFile);

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();        
 
            System.out.println("start query " + df.format(today));


                
            //*SolverConfig solverConfig1 = new SolverConfig(template); // 
//*            SolverConfig solverConfig2 = SolverConfig.createFromXmlResource(SOLVER_CONFIG2); //"org/optaplanner/examples/nqueens/solver/nqueensSolverConfig.xml");
            ///solverConfig1.getTerminationConfig().setSecondsSpentLimit(secondsSpentLimit);
            ///solverConfig1.getTerminationConfig().setUnimprovedSecondsSpentLimit(unimprovedSecondsSpentLimit);

/**             solverConfig2.withTerminationConfig(new TerminationConfig()
                    .withSecondsSpentLimit(secondsSpentLimit)
                    .withUnimprovedSecondsSpentLimit(unimprovedSecondsSpentLimit));                   
        
            SolverFactory<Schedule> solverFactory2 = SolverFactory.create(solverConfig2);
            Solver<Schedule> solver2 = solverFactory2.buildSolver();
*/
            // import solution
            solutionBusiness.importSolution(f);
            // calculate route
            Schedule projectSchedule = solutionBusiness.getSolution(); 
//*            projectSchedule.setStage(1);                          
            //*Schedule solvedSchedule = solver1.solve(projectSchedule);
            //*solutionBusiness.setSolution(solvedSchedule);
            Schedule solvedSchedule = solutionBusiness.solve(projectSchedule);  
            solvedSchedule.checkRightDelta();
//*            Schedule solvedSchedule = solutionBusiness.solve(projectSchedule);  
//*            solvedSchedule.setStage(2);   
            //projectSchedule.setStage(2);             
//*            Schedule solvedSchedule2 = solver2.solve(solvedSchedule);
            //Schedule solvedSchedule2 = solutionBusiness.solve(solvedSchedule);    
            solutionBusiness.setSolution(solvedSchedule); 

//***            solverAndPersistenceFrame.refreshPanel(solvedSchedule);
            //*solverAndPersistenceFrame.setSolutionLoaded(null);
                                
            // save result
            fres = new File(FilenameUtils.getFullPath(pFile), FilenameUtils.getBaseName(pFile) + "res.xml");                
            
            solutionBusiness.exportSolution(new ProjectJobSchedulingXmlExporter(), fres);  //exportSolution(fres);                
                
            today = Calendar.getInstance().getTime(); 
            System.out.println("end query " + df.format(today));
            
        } catch(Exception e){
           // if any error occurs
           e.printStackTrace();
        }        
        
    }     

    
}
