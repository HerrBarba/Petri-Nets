package analyzer.lexical;

public enum Token {
	type, closePar, openPar, openCurly, closeCurly, semi, colon, If, While, For, Switch,
	Case, Default, Break, opComp, id, opSum, opMinus, opMult, num, func, comma,
	openTag, closeTag, openSlashTag, closeSlashTag, equals, attr, string, petriNet, tag,
	whitespace, line, tab, missing, error, money
}
