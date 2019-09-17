import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './etf-execution-my-suffix.reducer';
import { IEtfExecutionMySuffix } from 'app/shared/model/etf-execution-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtfExecutionMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class EtfExecutionMySuffix extends React.Component<IEtfExecutionMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { etfExecutionList, match } = this.props;
    return (
      <div>
        <h2 id="etf-execution-my-suffix-heading">
          Etf Executions
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Etf Execution
          </Link>
        </h2>
        <div className="table-responsive">
          {etfExecutionList && etfExecutionList.length > 0 ? (
            <Table responsive aria-describedby="etf-execution-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Basket Order Id</th>
                  <th>Stock Code</th>
                  <th>Qty</th>
                  <th>Qty Odd</th>
                  <th>Price</th>
                  <th>Amount</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {etfExecutionList.map((etfExecution, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${etfExecution.id}`} color="link" size="sm">
                        {etfExecution.id}
                      </Button>
                    </td>
                    <td>{etfExecution.basketOrderId}</td>
                    <td>{etfExecution.stockCode}</td>
                    <td>{etfExecution.qty}</td>
                    <td>{etfExecution.qtyOdd}</td>
                    <td>{etfExecution.price}</td>
                    <td>{etfExecution.amount}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${etfExecution.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${etfExecution.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${etfExecution.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Etf Executions found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ etfExecution }: IRootState) => ({
  etfExecutionList: etfExecution.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfExecutionMySuffix);