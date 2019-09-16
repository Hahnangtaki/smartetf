import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtfExecutionMySuffix from './etf-execution-my-suffix';
import EtfExecutionMySuffixDetail from './etf-execution-my-suffix-detail';
import EtfExecutionMySuffixUpdate from './etf-execution-my-suffix-update';
import EtfExecutionMySuffixDeleteDialog from './etf-execution-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtfExecutionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtfExecutionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtfExecutionMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtfExecutionMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EtfExecutionMySuffixDeleteDialog} />
  </>
);

export default Routes;
