package blue.nez.pegasm;

import blue.nez.parser.PegAsmInstruction;
import blue.nez.parser.PegAsmContext;
import blue.nez.parser.ParserTerminationException;

public class ASMRbin extends ASMRbyte {
	public ASMRbin(PegAsmInstruction next) {
		super(0, next);
	}

	@Override
	public PegAsmInstruction exec(PegAsmContext<?> sc) throws ParserTerminationException {
		while (sc.prefetch() == 0 && !sc.eof()) {
			sc.move(1);
		}
		return this.next;
	}
}