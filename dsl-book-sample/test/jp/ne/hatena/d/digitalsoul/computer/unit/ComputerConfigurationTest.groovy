package jp.ne.hatena.d.digitalsoul.computer.unit;

import static org.junit.Assert.*;

import javax.annotation.processing.Processor;
import jp.ne.hatena.d.digitalsoul.computer.dsl.MyComputer;
import jp.ne.hatena.d.digitalsoul.computer.model.Computer;
import jp.ne.hatena.d.digitalsoul.computer.model.Processor;
import jp.ne.hatena.d.digitalsoul.computer.model.Disk;
import org.junit.Test;

public class ComputerConfigurationTest {

    /* モデルのテスト */

    @Test
    public void testProcessor() throws Exception {

        // setup
        Processor processor = new Processor(cores:2, type:"i386")
        Computer computer = new Computer(processor:processor)

        // verify
        assertEquals 2, computer.processor.cores
        assertEquals "i386", computer.processor.type
        
    }

    @Test
    public void testDisk() throws Exception {

        // setup
        Disk disk = new Disk(diskSize:75, diskSpeed:7200, diskInterface:"SATA")
        Computer computer = new Computer(disks:[disk])

        // verify
        assertEquals 75, computer.disks[0].diskSize
        assertEquals 7200, computer.disks[0].diskSpeed
        assertEquals "SATA", computer.disks[0].diskInterface
    }

    /* DSLのテスト */

    @Test
    public void shouldConfigureComputer() throws Exception {

        // setup
        Computer computer = new Computer()
        new MyComputer().configure computer

        // verify processor
        assertEquals 2, computer.processor.cores
        assertEquals "i386", computer.processor.type

        // verify disks
        assertEquals 150, computer.disks[0].diskSize
        assertEquals 75, computer.disks[1].diskSize
        assertEquals 7200, computer.disks[1].diskSpeed
        assertEquals "SATA", computer.disks[1].diskInterface
        
    }

}
