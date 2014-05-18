/*****************************************/
/* %rep%
 * @param 
 *
 */
/*****************************************/
EN_SB_RAM_RET SB_SysRam_Get_%rep%(const ST_SB_Cmn* a_pstCmn, %rep%* a_p%rep%Value)
{
	EN_SB_RAM_RET eRet;
	BOOL					bProcContinue = TRUE;

	/* アクセス権チェック */
	if(bProcContinue)
	{
		eRet = NG;
	}
	
	/* 範囲チェック */
	if(bProcContinue)
	{
		eRet = SAFE;
	}
	
	/* RAM格納 */
	if(bProcContinue)
	{
		if(A)
		{
			*a_p%rep%Value = stSysRamNa.%rep%;
			eRet = OK;
		}
		else
		{
			*a_p%rep%Value = stSysRamLi.%rep%;
			eRet = OK;
		}
	}

	return eRet;
}

