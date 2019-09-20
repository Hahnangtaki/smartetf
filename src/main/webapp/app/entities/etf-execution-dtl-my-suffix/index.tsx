import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtfExecutionDtlMySuffix from './etf-execution-dtl-my-suffix';
import EtfExecutionDtlMySuffixDetail from './etf-execution-dtl-my-suffix-detail';
import EtfExecutionDtlMySuffixUpdate from './etf-execution-dtl-my-suffix-update';
import EtfExecutionDtlMySuffixDeleteDialog from './etf-execution-dtl-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtfExecutionDtlMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtfExecutionDtlMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtfExecutionDtlMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtfExecutionDtlMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EtfExecutionDtlMySuffixDeleteDialog} />
  </>
);

export default Routes;
