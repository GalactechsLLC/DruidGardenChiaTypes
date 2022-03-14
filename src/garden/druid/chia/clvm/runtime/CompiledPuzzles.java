package garden.druid.chia.clvm.runtime;

import garden.druid.chia.types.bytes.Bytes;

public class CompiledPuzzles {

	public static final SExp TEST_MULTIPLE_GENERATOR_INPUT_ARGUMENTS_HEX = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff04ffff02ff04ffff04ff02ffff04ff05ffff04ff0bffff04ff82017fffff04ff8202ffffff04ffff02ff06ffff04ff02ffff04ff8205ffffff04ff17ffff04ff2fffff04ff5fffff04ff81bfff8080808080808080ff8080808080808080ff8080ffff04ffff01ffff02ffff03ff17ffff01ff04ffff02ff0bffff04ff2fffff04ff05ffff04ff5fffff04ff27ff808080808080ffff02ff04ffff04ff02ffff04ff05ffff04ff0bffff04ff37ffff04ff2fffff04ff5fff808080808080808080ff8080ff0180ff0effff0cff09ff0bff1780ffff0cff15ff2fff5f8080ff018080"));
	public static final SExp TEST_GENERATOR_DESERIALIZE = Parser.parse(Bytes.parseHexBinary("ff02ff02ffff04ff0bff808080"));
	public static final SExp SINGLETON_TOP_LAYER = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ffff03ffff18ff2fffff010180ffff01ff02ff36ffff04ff02ffff04ff05ffff04ff17ffff04ffff02ff26ffff04ff02ffff04ff0bff80808080ffff04ff2fffff04ff0bffff04ff5fff808080808080808080ffff01ff088080ff0180ffff04ffff01ffffffff4602ff3304ffff0101ff02ffff02ffff03ff05ffff01ff02ff5cffff04ff02ffff04ff0dffff04ffff0bff2cffff0bff24ff3880ffff0bff2cffff0bff2cffff0bff24ff3480ff0980ffff0bff2cff0bffff0bff24ff8080808080ff8080808080ffff010b80ff0180ff02ffff03ff0bffff01ff02ff32ffff04ff02ffff04ff05ffff04ff0bffff04ff17ffff04ffff02ff2affff04ff02ffff04ffff02ffff03ffff09ff23ff2880ffff0181b3ff8080ff0180ff80808080ff80808080808080ffff01ff02ffff03ff17ff80ffff01ff088080ff018080ff0180ffffffff0bffff0bff17ffff02ff3affff04ff02ffff04ff09ffff04ff2fffff04ffff02ff26ffff04ff02ffff04ff05ff80808080ff808080808080ff5f80ff0bff81bf80ff02ffff03ffff20ffff22ff4fff178080ffff01ff02ff7effff04ff02ffff04ff6fffff04ffff04ffff02ffff03ff4fffff01ff04ff23ffff04ffff02ff3affff04ff02ffff04ff09ffff04ff53ffff04ffff02ff26ffff04ff02ffff04ff05ff80808080ff808080808080ffff04ff81b3ff80808080ffff011380ff0180ffff02ff7cffff04ff02ffff04ff05ffff04ff1bffff04ffff21ff4fff1780ff80808080808080ff8080808080ffff01ff088080ff0180ffff04ffff09ffff18ff05ffff010180ffff010180ffff09ff05ffff01818f8080ff0bff2cffff0bff24ff3080ffff0bff2cffff0bff2cffff0bff24ff3480ff0580ffff0bff2cffff02ff5cffff04ff02ffff04ff07ffff04ffff0bff24ff2480ff8080808080ffff0bff24ff8080808080ffffff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff26ffff04ff02ffff04ff09ff80808080ffff02ff26ffff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff02ff5effff04ff02ffff04ff05ffff04ff0bffff04ffff02ff3affff04ff02ffff04ff09ffff04ff17ffff04ffff02ff26ffff04ff02ffff04ff05ff80808080ff808080808080ffff04ff17ffff04ff2fffff04ff5fffff04ff81bfff80808080808080808080ffff04ffff04ff20ffff04ff17ff808080ffff02ff7cffff04ff02ffff04ff05ffff04ffff02ff82017fffff04ffff04ffff04ff17ff2f80ffff04ffff04ff5fff81bf80ffff04ff0bff05808080ff8202ff8080ffff01ff80808080808080ffff02ff2effff04ff02ffff04ff05ffff04ff0bffff04ffff02ffff03ff3bffff01ff02ff22ffff04ff02ffff04ff05ffff04ff17ffff04ff13ffff04ff2bffff04ff5bffff04ff5fff808080808080808080ffff01ff02ffff03ffff09ff15ffff0bff13ff1dff2b8080ffff01ff0bff15ff17ff5f80ffff01ff088080ff018080ff0180ffff04ff17ffff04ff2fffff04ff5fffff04ff81bfffff04ff82017fff8080808080808080808080ff02ffff03ff05ffff011bffff010b80ff0180ff018080"));
	public static final SExp SINGLETON_LAUNCHER = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff04ffff04ff04ffff04ff05ffff04ff0bff80808080ffff04ffff04ff0affff04ffff02ff0effff04ff02ffff04ffff04ff05ffff04ff0bffff04ff17ff80808080ff80808080ff808080ff808080ffff04ffff01ff33ff3cff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff0effff04ff02ffff04ff09ff80808080ffff02ff0effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp SHA256TREE_MODULE = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ff02ffff04ff02ffff04ff05ff80808080ffff04ffff01ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff02ffff04ff02ffff04ff09ff80808080ffff02ff02ffff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp ROM_BOOTSTRAP_GENERATOR = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ff0cffff04ff02ffff04ffff02ff05ffff04ff08ffff04ff13ff80808080ff80808080ffff04ffff01ffffff02ffff01ff05ffff02ff3effff04ff02ffff04ff05ff8080808080ffff04ffff01ffffff81ff7fff81df81bfffffff02ffff03ffff09ff0bffff01818080ffff01ff04ff80ffff04ff05ff808080ffff01ff02ffff03ffff0aff0bff1880ffff01ff02ff1affff04ff02ffff04ffff02ffff03ffff0aff0bff1c80ffff01ff02ffff03ffff0aff0bff1480ffff01ff0880ffff01ff04ffff0effff18ffff011fff0b80ffff0cff05ff80ffff01018080ffff04ffff0cff05ffff010180ff80808080ff0180ffff01ff04ffff18ffff013fff0b80ffff04ff05ff80808080ff0180ff80808080ffff01ff04ff0bffff04ff05ff80808080ff018080ff0180ff04ffff0cff15ff80ff0980ffff04ffff0cff15ff0980ff808080ffff04ffff04ff05ff1380ffff04ff2bff808080ffff02ff16ffff04ff02ffff04ff09ffff04ffff02ff3effff04ff02ffff04ff15ff80808080ff8080808080ff02ffff03ffff09ffff0cff05ff80ffff010180ff1080ffff01ff02ff2effff04ff02ffff04ffff02ff3effff04ff02ffff04ffff0cff05ffff010180ff80808080ff80808080ffff01ff02ff12ffff04ff02ffff04ffff0cff05ffff010180ffff04ffff0cff05ff80ffff010180ff808080808080ff0180ff018080ff04ffff02ff16ffff04ff02ffff04ff09ff80808080ff0d80ffff04ff09ffff04ffff02ff1effff04ff02ffff04ff15ff80808080ffff04ff2dffff04ffff02ff15ff5d80ff7d80808080ffff02ffff03ff05ffff01ff04ffff02ff0affff04ff02ffff04ff09ff80808080ffff02ff16ffff04ff02ffff04ff0dff8080808080ff8080ff0180ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff1effff04ff02ffff04ff09ff80808080ffff02ff1effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp RATE_LIMIT = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ffff03ffff09ff81bfff2480ffff01ff04ffff04ff30ffff04ff5fffff04ffff02ff3effff04ff02ffff04ffff04ff81bfff81ff80ff80808080ff80808080ff81ff80ffff01ff04ffff04ff30ffff04ff05ffff04ffff02ff3effff04ff02ffff04ffff04ff81bfff81ff80ff80808080ff80808080ffff02ffff03ffff09ff81bfff3c80ffff01ff02ff2effff04ff02ffff04ff0bffff04ff17ffff04ff2fffff04ff81ffff80808080808080ffff01ff02ff22ffff04ff02ffff04ff2fffff04ff81ffff808080808080ff01808080ff0180ffff04ffff01ffffffffff02ffff03ffff15ff05ff0b80ffff01ff0101ffff01ff02ffff03ffff09ff05ff0b80ffff01ff0101ff8080ff018080ff018031ff5246ffff0333ff3c01ffffffff02ffff03ffff02ffff03ffff09ffff0bff820bfbff13ff8205fb80ff82017b80ffff01ff0101ffff01ff02ffff03ffff09ff05ff82017b80ffff01ff0101ff8080ff018080ff0180ffff01ff04ffff02ff26ffff04ff02ffff04ff82017bffff04ff13ffff04ff8202fbff808080808080ffff04ffff02ff2affff04ff02ffff04ff2bffff04ff5bffff04ff81bbff808080808080ffff04ffff02ff3affff04ff02ffff04ff13ffff04ffff10ff81bbff8202fb80ff8080808080ff80808080ffff01ffff08808080ff0180ff04ff34ffff04ff05ffff04ffff11ff0bffff10ff17ff2f8080ff80808080ffff04ff2cffff04ffff0bff05ff0bff1780ff808080ff04ff34ffff04ff05ffff04ff0bff80808080ffffff04ff38ffff04ffff0bff05ff0bff1780ff808080ff02ffff03ffff02ff20ffff04ff02ffff04ffff12ff05ff1780ffff04ffff12ff0bff2f80ff8080808080ffff01ff04ff28ffff04ff05ff808080ffff01ffff08808080ff0180ffff02ffff03ffff02ffff03ffff09ffff0bff8217efff81afff822fef80ff4f80ffff01ff0101ffff01ff02ffff03ffff09ff17ff4f80ffff01ff0101ff8080ff018080ff0180ffff01ff04ffff02ff36ffff04ff02ffff04ff820befffff04ff8205efffff04ff05ffff04ff0bff80808080808080ffff04ffff02ff32ffff04ff02ffff04ff81afffff04ff82016fffff04ff8205efffff04ff825fefff80808080808080ffff04ffff02ff26ffff04ff02ffff04ff4fffff04ff81afffff04ff82016fff808080808080ffff04ffff02ff3affff04ff02ffff04ff8202efffff04ff8205efff8080808080ff8080808080ffff01ffff08808080ff0180ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff3effff04ff02ffff04ff09ff80808080ffff02ff3effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp RATE_LIMIT_AGGREGATION = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff04ffff04ff06ffff04ff0bff808080ffff04ffff04ff04ffff04ffff0bffff0bff17ff05ff2f80ff0b80ff808080ff808080ffff04ffff01ff3d46ff018080"));
	public static final SExp POOL_WAITINGROOM_INNER_PUZZLE = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ffff03ff82017fffff01ff04ffff04ff1cffff04ff5fff808080ffff04ffff04ff12ffff04ff8205ffffff04ff8206bfff80808080ffff04ffff04ff08ffff04ff17ffff04ffff02ff1effff04ff02ffff04ffff04ff8205ffffff04ff8202ffff808080ff80808080ff80808080ff80808080ffff01ff02ff16ffff04ff02ffff04ff05ffff04ff8204bfffff04ff8206bfffff04ff8202ffffff04ffff0bffff19ff2fffff18ffff019100ffffffffffffffffffffffffffffffffff8205ff8080ff0bff8202ff80ff808080808080808080ff0180ffff04ffff01ffff32ff3d52ffff333effff04ffff04ff12ffff04ff0bffff04ff17ff80808080ffff04ffff04ff12ffff04ff05ffff04ff2fff80808080ffff04ffff04ff1affff04ff5fff808080ffff04ffff04ff14ffff04ffff0bff5fffff012480ff808080ff8080808080ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff1effff04ff02ffff04ff09ff80808080ffff02ff1effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp POOL_MEMBER_INNER_PUZZLE = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ffff03ff8202ffffff01ff02ff16ffff04ff02ffff04ff05ffff04ff8204bfffff04ff8206bfffff04ff82017fffff04ffff0bffff19ff2fffff18ffff019100ffffffffffffffffffffffffffffffffff8202ff8080ff0bff82017f80ff8080808080808080ffff01ff04ffff04ff08ffff04ff17ffff04ffff02ff1effff04ff02ffff04ff82017fff80808080ff80808080ffff04ffff04ff1cffff04ff5fffff04ff8206bfff80808080ff80808080ff0180ffff04ffff01ffff32ff3d33ff3effff04ffff04ff1cffff04ff0bffff04ff17ff80808080ffff04ffff04ff1cffff04ff05ffff04ff2fff80808080ffff04ffff04ff0affff04ff5fff808080ffff04ffff04ff14ffff04ffff0bff5fffff012480ff808080ff8080808080ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff1effff04ff02ffff04ff09ff80808080ffff02ff1effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp P2_SINGLETON = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff04ffff04ff18ffff04ffff0bffff02ff2effff04ff02ffff04ff05ffff04ff2fffff04ffff02ff3effff04ff02ffff04ffff04ff05ffff04ff0bff178080ff80808080ff808080808080ff5f80ff808080ffff04ffff04ff2cffff01ff248080ffff04ffff04ff10ffff04ff5fff808080ff80808080ffff04ffff01ffffff463fff02ff3c04ffff01ff0102ffff02ffff03ff05ffff01ff02ff16ffff04ff02ffff04ff0dffff04ffff0bff3affff0bff12ff3c80ffff0bff3affff0bff3affff0bff12ff2a80ff0980ffff0bff3aff0bffff0bff12ff8080808080ff8080808080ffff010b80ff0180ffff0bff3affff0bff12ff1480ffff0bff3affff0bff3affff0bff12ff2a80ff0580ffff0bff3affff02ff16ffff04ff02ffff04ff07ffff04ffff0bff12ff1280ff8080808080ffff0bff12ff8080808080ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff3effff04ff02ffff04ff09ff80808080ffff02ff3effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp P2_SINGLETON_OR_DELAYER_PUZHASH = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ffff03ff82017fffff01ff04ffff04ff38ffff04ffff0bffff02ff2effff04ff02ffff04ff05ffff04ff81bfffff04ffff02ff3effff04ff02ffff04ffff04ff05ffff04ff0bff178080ff80808080ff808080808080ff82017f80ff808080ffff04ffff04ff3cffff01ff248080ffff04ffff04ff28ffff04ff82017fff808080ff80808080ffff01ff04ffff04ff24ffff04ff2fff808080ffff04ffff04ff2cffff04ff5fffff04ff81bfff80808080ffff04ffff04ff10ffff04ff81bfff808080ff8080808080ff0180ffff04ffff01ffffff49ff463fffff5002ff333cffff04ff0101ffff02ff02ffff03ff05ffff01ff02ff36ffff04ff02ffff04ff0dffff04ffff0bff26ffff0bff2aff1280ffff0bff26ffff0bff26ffff0bff2aff3a80ff0980ffff0bff26ff0bffff0bff2aff8080808080ff8080808080ffff010b80ff0180ffff0bff26ffff0bff2aff3480ffff0bff26ffff0bff26ffff0bff2aff3a80ff0580ffff0bff26ffff02ff36ffff04ff02ffff04ff07ffff04ffff0bff2aff2a80ff8080808080ffff0bff2aff8080808080ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff3effff04ff02ffff04ff09ff80808080ffff02ff3effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp P2_PUZZLE_HASH = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ffff03ffff09ff05ffff02ff02ffff04ff02ffff04ff0bff8080808080ffff01ff02ff0bff1780ffff01ff088080ff0180ffff04ffff01ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff02ffff04ff02ffff04ff09ff80808080ffff02ff02ffff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp P2_M_OF_N_DELEGATE_DIRECT = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ffff03ffff09ff05ffff02ff16ffff04ff02ffff04ff17ff8080808080ffff01ff02ff0cffff04ff02ffff04ffff02ff0affff04ff02ffff04ff17ffff04ff0bff8080808080ffff04ffff02ff1effff04ff02ffff04ff2fff80808080ffff04ff2fffff04ff5fff80808080808080ffff01ff088080ff0180ffff04ffff01ffff31ff02ffff03ff05ffff01ff04ffff04ff08ffff04ff09ffff04ff0bff80808080ffff02ff0cffff04ff02ffff04ff0dffff04ff0bffff04ff17ffff04ff2fff8080808080808080ffff01ff02ff17ff2f8080ff0180ffff02ffff03ff05ffff01ff02ffff03ff09ffff01ff04ff13ffff02ff0affff04ff02ffff04ff0dffff04ff1bff808080808080ffff01ff02ff0affff04ff02ffff04ff0dffff04ff1bff808080808080ff0180ff8080ff0180ffff02ffff03ff05ffff01ff10ffff02ff16ffff04ff02ffff04ff0dff80808080ffff02ffff03ff09ffff01ff0101ff8080ff018080ff8080ff0180ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff1effff04ff02ffff04ff09ff80808080ffff02ff1effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp P2_DELEGATED_PUZZLE = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff04ffff04ff04ffff04ff05ffff04ffff02ff06ffff04ff02ffff04ff0bff80808080ff80808080ffff02ff0bff178080ffff04ffff01ff32ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff06ffff04ff02ffff04ff09ff80808080ffff02ff06ffff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp P2_DELEGATED_PUZZLE_OR_HIDDEN_PUZZLE = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ffff03ff0bffff01ff02ffff03ffff09ff05ffff1dff0bffff1effff0bff0bffff02ff06ffff04ff02ffff04ff17ff8080808080808080ffff01ff02ff17ff2f80ffff01ff088080ff0180ffff01ff04ffff04ff04ffff04ff05ffff04ffff02ff06ffff04ff02ffff04ff17ff80808080ff80808080ffff02ff17ff2f808080ff0180ffff04ffff01ff32ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff06ffff04ff02ffff04ff09ff80808080ffff02ff06ffff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp P2_DELEGATED_CONDITIONS = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff04ffff04ff04ffff04ff05ffff04ffff02ff06ffff04ff02ffff04ff0bff80808080ff80808080ff0b80ffff04ffff01ff32ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff06ffff04ff02ffff04ff09ff80808080ffff02ff06ffff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp P2_CONDITIONS = Parser.parse(Bytes.parseHexBinary("ff04ffff0101ff0280"));
	public static final SExp LOCK_INNER_PUZZLE = Parser.parse(Bytes.parseHexBinary("ff01ff8080"));
	public static final SExp GENESIS_BY_PUZZLE_HASH_WITH_0 = Parser.parse(Bytes.parseHexBinary("ff02ffff03ffff09ff5bff8080ffff01ff0101ffff01ff02ffff03ffff02ffff03ffff09ffff0bff47ff81a7ff82016780ff1380ffff01ff02ffff03ffff09ff81a7ff0280ffff01ff0101ff8080ff0180ff8080ff0180ffff01ff0101ff8080ff018080ff0180"));
	public static final SExp GENESIS_BY_COIN_ID_WITH_0 = Parser.parse(Bytes.parseHexBinary("ff02ffff03ffff09ff5bff8080ffff01ff0101ffff01ff02ffff03ffff09ff13ff0280ffff01ff0101ff8080ff018080ff0180"));
	public static final SExp GENERATOR_FOR_SINGLE_COIN = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ff0cffff04ff02ffff04ffff05ffff02ff05ffff04ff0affff04ff13ff8080808080ffff04ff17ff8080808080ffff04ffff01ffffff09ffff0bff09ffff02ff0effff04ff02ffff04ff15ff80808080ff2d80ff0b80ff02ffff03ff05ffff01ff02ffff03ffff02ff08ffff04ff02ffff04ff09ffff04ff0bff8080808080ffff01ff04ff29ffff04ff81b9ff808080ffff01ff02ff0cffff04ff02ffff04ff0dffff04ff0bff808080808080ff0180ffff01ff088080ff0180ffff02ffff01ff05ffff02ff3effff04ff02ffff04ff05ff8080808080ffff04ffff01ffffff81ff7fff81df81bfffffff02ffff03ffff09ff0bffff01818080ffff01ff04ff80ffff04ff05ff808080ffff01ff02ffff03ffff0aff0bff1880ffff01ff02ff1affff04ff02ffff04ffff02ffff03ffff0aff0bff1c80ffff01ff02ffff03ffff0aff0bff1480ffff01ff0880ffff01ff04ffff0effff18ffff011fff0b80ffff0cff05ff80ffff01018080ffff04ffff0cff05ffff010180ff80808080ff0180ffff01ff04ffff18ffff013fff0b80ffff04ff05ff80808080ff0180ff80808080ffff01ff04ff0bffff04ff05ff80808080ff018080ff0180ff04ffff0cff15ff80ff0980ffff04ffff0cff15ff0980ff808080ffff04ffff04ff05ff1380ffff04ff2bff808080ffff02ff16ffff04ff02ffff04ff09ffff04ffff02ff3effff04ff02ffff04ff15ff80808080ff8080808080ff02ffff03ffff09ffff0cff05ff80ffff010180ff1080ffff01ff02ff2effff04ff02ffff04ffff02ff3effff04ff02ffff04ffff0cff05ffff010180ff80808080ff80808080ffff01ff02ff12ffff04ff02ffff04ffff0cff05ffff010180ffff04ffff0cff05ff80ffff010180ff808080808080ff0180ff018080ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff0effff04ff02ffff04ff09ff80808080ffff02ff0effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp DID_INNER_PUZZLE = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ffff03ff5fffff01ff02ffff03ffff09ff5fffff010180ffff01ff04ffff04ff24ffff04ff8202ffffff04ff81bfff80808080ffff04ffff04ff20ffff04ff05ffff04ffff02ff7effff04ff02ffff04ffff04ff8202ffffff04ff81bfffff04ff82017fff80808080ff80808080ff80808080ffff02ff36ffff04ff02ffff04ff82017fff808080808080ffff01ff02ffff03ff8217ffffff01ff02ffff03ffff09ffff02ff7effff04ff02ffff04ff8217ffff80808080ff0b80ffff01ff02ff3affff04ff02ffff04ff8201efffff04ff17ffff04ff8217ffffff04ff818fffff04ffff04ffff04ff24ffff04ff82017fffff04ff81bfff80808080ff8080ffff04ff82017fffff04ff8205ffffff04ff820bffffff01ff808080808080808080808080ffff01ff088080ff0180ffff01ff088080ff018080ff0180ffff01ff04ffff04ff24ffff01ff00ff818f8080ffff04ffff04ff24ffff04ff82017fffff04ff81bfff80808080ffff04ffff04ff20ffff04ff05ffff04ffff02ff7effff04ff02ffff04ffff04ff81bfffff04ff82017fff808080ff80808080ff80808080ff8080808080ff0180ffff04ffff01ffffffff3231ff3d02ffff333cff3eff0401ffffff0102ffff02ffff03ff05ffff01ff02ff2affff04ff02ffff04ff0dffff04ffff0bff32ffff0bff7cff5c80ffff0bff32ffff0bff32ffff0bff7cff2280ff0980ffff0bff32ff0bffff0bff7cff8080808080ff8080808080ffff010b80ff0180ff02ffff03ff17ffff01ff02ffff03ff82027fffff01ff02ff3affff04ff02ffff04ff05ffff04ff0bffff04ff37ffff04ff2fffff04ffff04ffff04ff28ffff04ffff0bffff0bffff02ff26ffff04ff02ffff04ff05ffff04ff27ffff04ff82047fffff04ff820a7fffff04ff82167fff8080808080808080ffff02ff7effff04ff02ffff04ffff02ff2effff04ff02ffff04ff2fffff04ff81bfffff04ff8202ffff808080808080ff8080808080ff2f80ff808080ff5f80ffff04ff81bfffff04ff82037fffff04ff8202ffffff04ffff10ff8205ffffff010180ff808080808080808080808080ffff01ff02ff3affff04ff02ffff04ff05ffff04ff37ffff04ff2fffff04ff5fffff04ff81bfffff04ff82037fffff04ff8202ffffff04ff8205ffff808080808080808080808080ff0180ffff01ff02ffff03ffff15ff8205ffff0b80ffff01ff04ffff04ff30ffff04ff8202ffffff04ff81bfff80808080ff5f80ffff01ff02ffff03ffff09ff8205ffff0b80ffff01ff04ffff04ff30ffff04ff8202ffffff04ff81bfff80808080ff5f80ffff01ff088080ff018080ff018080ff0180ffffff0bff17ffff02ff5effff04ff02ffff04ff09ffff04ff2fffff04ffff02ff7effff04ff02ffff04ffff04ff09ffff04ff0bff1d8080ff80808080ff808080808080ff5f80ff02ffff03ff05ffff01ff04ffff02ffff03ff11ffff01ff04ffff02ffff03ffff09ff11ffff010180ffff0134ffff012c80ff0180ffff04ff19ff808080ffff01ff04ff24ffff04ff19ffff01ff8080808080ff0180ffff02ff36ffff04ff02ffff04ff0dff8080808080ff8080ff0180ffff04ffff0101ffff04ffff04ff34ffff04ff05ff808080ffff04ffff04ff30ffff04ff17ffff04ff0bff80808080ff80808080ffff0bff32ffff0bff7cff3880ffff0bff32ffff0bff32ffff0bff7cff2280ff0580ffff0bff32ffff02ff2affff04ff02ffff04ff07ffff04ffff0bff7cff7c80ff8080808080ffff0bff7cff8080808080ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff7effff04ff02ffff04ff09ff80808080ffff02ff7effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ff018080"));
	public static final SExp DECOMPRESS_PUZZLE = Parser.parse(Bytes.parseHexBinary("ff02ff02ffff04ffff0eff05ff0bff1780ff808080"));
	public static final SExp DECOMPRESS_COIN_SPEND_ENTRY = Parser.parse(Bytes.parseHexBinary("ff04ff4fffff04ffff02ff05ffff04ff02ffff04ff0bffff04ff82012fffff04ff17ff808080808080ffff04ff82014fff8201af808080"));
	public static final SExp DECOMPRESS_COIN_SPEND_ENTRY_WITH_PREFIX = Parser.parse(Bytes.parseHexBinary("ff04ff47ffff04ffff02ff05ffff04ff02ffff04ff0bffff04ff8197ffff01ff84ff0180808080808080ffff04ff81a7ff81d7808080"));
	public static final SExp CHIALISP_DESERIALIZATION = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff05ffff02ff3effff04ff02ffff04ff05ff8080808080ffff04ffff01ffffff81ff7fff81df81bfffffff02ffff03ffff09ff0bffff01818080ffff01ff04ff80ffff04ff05ff808080ffff01ff02ffff03ffff0aff0bff1880ffff01ff02ff1affff04ff02ffff04ffff02ffff03ffff0aff0bff1c80ffff01ff02ffff03ffff0aff0bff1480ffff01ff0880ffff01ff04ffff0effff18ffff011fff0b80ffff0cff05ff80ffff01018080ffff04ffff0cff05ffff010180ff80808080ff0180ffff01ff04ffff18ffff013fff0b80ffff04ff05ff80808080ff0180ff80808080ffff01ff04ff0bffff04ff05ff80808080ff018080ff0180ff04ffff0cff15ff80ff0980ffff04ffff0cff15ff0980ff808080ffff04ffff04ff05ff1380ffff04ff2bff808080ffff02ff16ffff04ff02ffff04ff09ffff04ffff02ff3effff04ff02ffff04ff15ff80808080ff8080808080ff02ffff03ffff09ffff0cff05ff80ffff010180ff1080ffff01ff02ff2effff04ff02ffff04ffff02ff3effff04ff02ffff04ffff0cff05ffff010180ff80808080ff80808080ffff01ff02ff12ffff04ff02ffff04ffff0cff05ffff010180ffff04ffff0cff05ff80ffff010180ff808080808080ff0180ff018080"));
	public static final SExp CC = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff02ff7affff04ff02ffff04ffff04ff05ffff04ffff02ff2effff04ff02ffff04ff05ff80808080ffff04ff0bffff04ffff02ff2effff04ff02ffff04ff0bff80808080ff8080808080ffff04ffff02ff17ff2f80ffff04ff5fffff04ff81bfffff04ff82017fffff04ff8202ffff808080808080808080ffff04ffff01ffffffff3d46ff333cffffff02ff5effff04ff02ffff04ffff02ff2cffff04ff02ffff04ff09ffff04ff15ffff04ff5dffff04ff0bff80808080808080ffff04ff09ffff04ff15ffff04ff5dffff04ff0bff8080808080808080ff0bff09ff15ff2d80ffff02ff5cffff04ff02ffff04ff05ffff04ff07ff8080808080ffff04ffff0102ffff04ffff04ffff0101ff0580ffff04ffff02ff7cffff04ff02ffff04ff0bffff01ff0180808080ff80808080ff02ffff03ff05ffff01ff04ffff0104ffff04ffff04ffff0101ff0980ffff04ffff02ff7cffff04ff02ffff04ff0dffff04ff0bff8080808080ff80808080ffff010b80ff0180ffffff2dff02ffff03ff15ffff01ff02ff5affff04ff02ffff04ff0bffff04ff09ffff04ff1dff808080808080ffff01ff02ffff02ff22ffff04ff02ffff04ff0bff80808080ffff04ff0bffff04ff09ffff04ff1dff808080808080ff0180ffff02ffff03ff0bffff01ff02ffff03ffff09ff05ff1380ffff01ff0101ffff01ff02ff2affff04ff02ffff04ff05ffff04ff1bff808080808080ff0180ff8080ff0180ffff09ff13ffff0bff27ffff02ff24ffff04ff02ffff04ff05ffff04ff57ff8080808080ff81b78080ff02ffff03ffff02ff32ffff04ff02ffff04ff17ffff04ff05ff8080808080ffff01ff02ffff03ffff02ff32ffff04ff02ffff04ff2fffff04ff05ff8080808080ffff01ff02ffff03ffff02ff32ffff04ff02ffff04ff5fffff04ff05ff8080808080ffff01ff04ffff04ff30ffff04ffff02ff34ffff04ff02ffff04ff4fff80808080ff808080ffff04ffff04ff38ffff04ffff02ff2effff04ff02ffff04ffff04ff27ffff04ff81bfff808080ff80808080ff808080ffff04ffff04ff20ffff04ffff0bffff02ff34ffff04ff02ffff04ff819fff80808080ffff02ff2effff04ff02ffff04ffff04ff4fffff04ffff10ff81bfffff11ff8202cfffff02ff36ffff04ff02ffff04ff0bff808080808080ff808080ff8080808080ff808080ffff02ff26ffff04ff02ffff04ff0bffff04ff05ff8080808080808080ffff01ff088080ff0180ffff01ff088080ff0180ffff01ff088080ff0180ffffff02ffff03ff05ffff01ff04ffff02ffff03ffff09ff11ff2880ffff01ff04ff28ffff04ffff02ff24ffff04ff02ffff04ff0bffff04ff29ff8080808080ffff04ff59ff80808080ffff01ff02ffff03ffff09ff11ff3880ffff01ff0880ffff010980ff018080ff0180ffff02ff26ffff04ff02ffff04ff0dffff04ff0bff808080808080ff8080ff0180ff02ffff03ff05ffff01ff10ffff02ffff03ffff09ff11ff2880ffff0159ff8080ff0180ffff02ff36ffff04ff02ffff04ff0dff8080808080ff8080ff0180ffff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff2effff04ff02ffff04ff09ff80808080ffff02ff2effff04ff02ffff04ff0dff8080808080ffff01ff0bffff0101ff058080ff0180ffff02ff7effff04ff02ffff04ff05ffff04ff07ff8080808080ff02ffff03ffff07ff0580ffff01ff0bffff0102ffff02ff7effff04ff02ffff04ff09ffff04ff0bff8080808080ffff02ff7effff04ff02ffff04ff0dffff04ff0bff808080808080ffff01ff02ffff03ffff02ff2affff04ff02ffff04ff05ffff04ff0bff8080808080ffff0105ffff01ff0bffff0101ff058080ff018080ff0180ff018080"));
	
	public static final SExp CALCULATE_SYNTHETIC_PUBLIC_KEY = Parser.parse(Bytes.parseHexBinary("ff1dff02ffff1effff0bff02ff05808080"));
	public static final SExp BLOCK_PROGRAM_ZERO = Parser.parse(Bytes.parseHexBinary("ff02ffff01ff04ffff02ff02ffff04ff02ffff04ff05ffff04ff0bffff04ff5fffff04ff81bfffff04ffff0cff82027fff17ff2f80ff8080808080808080ff8080ffff04ffff01ff02ffff03ff17ffff01ff04ffff02ff0bffff04ff2fffff04ff05ffff04ff5fffff04ff27ff808080808080ffff02ff02ffff04ff02ffff04ff05ffff04ff0bffff04ff37ffff04ff2fffff04ff5fff808080808080808080ff8080ff0180ff018080"));
}