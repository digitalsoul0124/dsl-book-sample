package jp.ne.hatena.d.digitalsoul.computer.dsl;

import jp.ne.hatena.d.digitalsoul.computer.model.Computer;

public class MyComputer extends ComputerBuilder {

    void configureComputer() { 

        computer {
            processor { p ->
                p.cores = 2
                p.type = "i386"
            }
            disk { d ->
                d.diskSize = 150
            }
            disk { d ->
                d.diskSize = 75
                d.diskSpeed = 7200
                d.diskInterface = "SATA"
            }
        }
    }

}
