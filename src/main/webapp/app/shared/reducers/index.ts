import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import globalParameter, {
  GlobalParameterMySuffixState
} from 'app/entities/global-parameter-my-suffix/global-parameter-my-suffix.reducer';
// prettier-ignore
import bankCustody, {
  BankCustodyMySuffixState
} from 'app/entities/bank-custody-my-suffix/bank-custody-my-suffix.reducer';
// prettier-ignore
import mi, {
  MiMySuffixState
} from 'app/entities/mi-my-suffix/mi-my-suffix.reducer';
// prettier-ignore
import dealerParticipant, {
  DealerParticipantMySuffixState
} from 'app/entities/dealer-participant-my-suffix/dealer-participant-my-suffix.reducer';
// prettier-ignore
import etfProduct, {
  EtfProductMySuffixState
} from 'app/entities/etf-product-my-suffix/etf-product-my-suffix.reducer';
// prettier-ignore
import etfProductDealer, {
  EtfProductDealerMySuffixState
} from 'app/entities/etf-product-dealer-my-suffix/etf-product-dealer-my-suffix.reducer';
// prettier-ignore
import etfUnderlying, {
  EtfUnderlyingMySuffixState
} from 'app/entities/etf-underlying-my-suffix/etf-underlying-my-suffix.reducer';
// prettier-ignore
import etfUnderlyingDtl, {
  EtfUnderlyingDtlMySuffixState
} from 'app/entities/etf-underlying-dtl-my-suffix/etf-underlying-dtl-my-suffix.reducer';
// prettier-ignore
import etfHistory, {
  EtfHistoryMySuffixState
} from 'app/entities/etf-history-my-suffix/etf-history-my-suffix.reducer';
// prettier-ignore
import subscript, {
  SubscriptMySuffixState
} from 'app/entities/subscript-my-suffix/subscript-my-suffix.reducer';
// prettier-ignore
import redemption, {
  RedemptionMySuffixState
} from 'app/entities/redemption-my-suffix/redemption-my-suffix.reducer';
// prettier-ignore
import etfExecution, {
  EtfExecutionMySuffixState
} from 'app/entities/etf-execution-my-suffix/etf-execution-my-suffix.reducer';
// prettier-ignore
import etfExecutionDtl, {
  EtfExecutionDtlMySuffixState
} from 'app/entities/etf-execution-dtl-my-suffix/etf-execution-dtl-my-suffix.reducer';
// prettier-ignore
import portofolio, {
  PortofolioMySuffixState
} from 'app/entities/portofolio-my-suffix/portofolio-my-suffix.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly globalParameter: GlobalParameterMySuffixState;
  readonly bankCustody: BankCustodyMySuffixState;
  readonly mi: MiMySuffixState;
  readonly dealerParticipant: DealerParticipantMySuffixState;
  readonly etfProduct: EtfProductMySuffixState;
  readonly etfProductDealer: EtfProductDealerMySuffixState;
  readonly etfUnderlying: EtfUnderlyingMySuffixState;
  readonly etfUnderlyingDtl: EtfUnderlyingDtlMySuffixState;
  readonly etfHistory: EtfHistoryMySuffixState;
  readonly subscript: SubscriptMySuffixState;
  readonly redemption: RedemptionMySuffixState;
  readonly etfExecution: EtfExecutionMySuffixState;
  readonly etfExecutionDtl: EtfExecutionDtlMySuffixState;
  readonly portofolio: PortofolioMySuffixState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  globalParameter,
  bankCustody,
  mi,
  dealerParticipant,
  etfProduct,
  etfProductDealer,
  etfUnderlying,
  etfUnderlyingDtl,
  etfHistory,
  subscript,
  redemption,
  etfExecution,
  etfExecutionDtl,
  portofolio,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
