package garden.druid.chia.clvm.runtime;

import java.math.BigInteger;

public class Costs {
	public final static BigInteger 
		IF_COST = BigInteger.valueOf(33),
		CONS_COST = BigInteger.valueOf(50),
		FIRST_COST = BigInteger.valueOf(30),
		REST_COST = BigInteger.valueOf(30),
		LISTP_COST = BigInteger.valueOf(19),

		MALLOC_COST_PER_BYTE = BigInteger.valueOf(10),

		ARITH_BASE_COST = BigInteger.valueOf(99),
		ARITH_COST_PER_BYTE = BigInteger.valueOf(3),
		ARITH_COST_PER_ARG = BigInteger.valueOf(320),

		LOG_BASE_COST = BigInteger.valueOf(100),
		LOG_COST_PER_BYTE = BigInteger.valueOf(3),
		LOG_COST_PER_ARG = BigInteger.valueOf(264),

		GRS_BASE_COST = BigInteger.valueOf(117),
		GRS_COST_PER_BYTE = BigInteger.valueOf(1),

		EQ_BASE_COST = BigInteger.valueOf(117),
		EQ_COST_PER_BYTE = BigInteger.valueOf(1),

		GR_BASE_COST = BigInteger.valueOf(498),
		GR_COST_PER_BYTE = BigInteger.valueOf(2),

		DIVMOD_BASE_COST = BigInteger.valueOf(1116),
		DIVMOD_COST_PER_BYTE = BigInteger.valueOf(6),

		DIV_BASE_COST = BigInteger.valueOf(988),
		DIV_COST_PER_BYTE = BigInteger.valueOf(4),

		SHA256_BASE_COST = BigInteger.valueOf(87),
		SHA256_COST_PER_ARG = BigInteger.valueOf(134),
		SHA256_COST_PER_BYTE = BigInteger.valueOf(2),

		POINT_ADD_BASE_COST = BigInteger.valueOf(101094),
		POINT_ADD_COST_PER_ARG = BigInteger.valueOf(1343980),

		PUBKEY_BASE_COST = BigInteger.valueOf(1325730),
		PUBKEY_COST_PER_BYTE = BigInteger.valueOf(38),

		MUL_BASE_COST = BigInteger.valueOf(92),
		MUL_COST_PER_OP = BigInteger.valueOf(885),
		MUL_LINEAR_COST_PER_BYTE = BigInteger.valueOf(6),
		MUL_SQUARE_COST_PER_BYTE_DIVIDER = BigInteger.valueOf(128),

		STRLEN_BASE_COST = BigInteger.valueOf(173),
		STRLEN_COST_PER_BYTE = BigInteger.valueOf(1),

		PATH_LOOKUP_BASE_COST = BigInteger.valueOf(40),
		PATH_LOOKUP_COST_PER_LEG = BigInteger.valueOf(4),
		PATH_LOOKUP_COST_PER_ZERO_BYTE = BigInteger.valueOf(4),

		CONCAT_BASE_COST = BigInteger.valueOf(142),
		CONCAT_COST_PER_ARG = BigInteger.valueOf(135),
		CONCAT_COST_PER_BYTE = BigInteger.valueOf(3),

		BOOL_BASE_COST = BigInteger.valueOf(200),
		BOOL_COST_PER_ARG = BigInteger.valueOf(300),

		ASHIFT_BASE_COST = BigInteger.valueOf(596),
		ASHIFT_COST_PER_BYTE = BigInteger.valueOf(3),

		LSHIFT_BASE_COST = BigInteger.valueOf(277),
		LSHIFT_COST_PER_BYTE = BigInteger.valueOf(3),

		LOGNOT_BASE_COST = BigInteger.valueOf(331),
		LOGNOT_COST_PER_BYTE = BigInteger.valueOf(3),

		APPLY_COST = BigInteger.valueOf(90),
		QUOTE_COST = BigInteger.valueOf(20);
}
