import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtfHistoryMySuffix from './etf-history-my-suffix';
import EtfHistoryMySuffixDetail from './etf-history-my-suffix-detail';
import EtfHistoryMySuffixUpdate from './etf-history-my-suffix-update';
import EtfHistoryMySuffixDeleteDialog from './etf-history-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtfHistoryMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtfHistoryMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtfHistoryMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtfHistoryMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EtfHistoryMySuffixDeleteDialog} />
  </>
);

export default Routes;
