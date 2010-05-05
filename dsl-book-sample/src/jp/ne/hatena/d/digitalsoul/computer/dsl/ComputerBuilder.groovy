package jp.ne.hatena.d.digitalsoul.computer.dsl;

import groovy.lang.Closure;
import jp.ne.hatena.d.digitalsoul.computer.model.Computer;
import jp.ne.hatena.d.digitalsoul.computer.model.Disk;
import jp.ne.hatena.d.digitalsoul.computer.model.Processor;

public abstract class ComputerBuilder {

    Computer computer

    void configure(Computer computer) {
        this.computer = computer
        configureComputer()
    }

    abstract void configureComputer()

    void computer(Closure configureComputer) {
        configureComputer.call()
    }

    void processor(Closure configureProcessor) {
        Processor processor = new Processor()
        configureProcessor.call(processor)
        computer.processor = processor
    }

    void disk(Closure configureDisk) {
        Disk disk = new Disk()
        configureDisk.call(disk)
        computer.disks.add(disk)
    }

    
    
}
