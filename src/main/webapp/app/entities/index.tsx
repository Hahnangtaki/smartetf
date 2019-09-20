import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import GlobalParameterMySuffix from './global-parameter-my-suffix';
import BankCustodyMySuffix from './bank-custody-my-suffix';
import MiMySuffix from './mi-my-suffix';
import DealerParticipantMySuffix from './dealer-participant-my-suffix';
import EtfProductMySuffix from './etf-product-my-suffix';
import EtfUnderlyingMySuffix from './etf-underlying-my-suffix';
import EtfUnderlyingDtlMySuffix from './etf-underlying-dtl-my-suffix';
import EtfHistoryMySuffix from './etf-history-my-suffix';
import SubscriptMySuffix from './subscript-my-suffix';
import RedemptionMySuffix from './redemption-my-suffix';
import EtfExecutionMySuffix from './etf-execution-my-suffix';
import EtfExecutionDtlMySuffix from './etf-execution-dtl-my-suffix';
import PortofolioMySuffix from './portofolio-my-suffix';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/global-parameter-my-suffix`} component={GlobalParameterMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/bank-custody-my-suffix`} component={BankCustodyMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/mi-my-suffix`} component={MiMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/dealer-participant-my-suffix`} component={DealerParticipantMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/etf-product-my-suffix`} component={EtfProductMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/etf-underlying-my-suffix`} component={EtfUnderlyingMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/etf-underlying-dtl-my-suffix`} component={EtfUnderlyingDtlMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/etf-history-my-suffix`} component={EtfHistoryMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/subscript-my-suffix`} component={SubscriptMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/redemption-my-suffix`} component={RedemptionMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/etf-execution-my-suffix`} component={EtfExecutionMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/etf-execution-dtl-my-suffix`} component={EtfExecutionDtlMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/portofolio-my-suffix`} component={PortofolioMySuffix} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
