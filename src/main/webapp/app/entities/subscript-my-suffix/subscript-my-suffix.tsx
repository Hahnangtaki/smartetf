import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './subscript-my-suffix.reducer';
import { ISubscriptMySuffix } from 'app/shared/model/subscript-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISubscriptMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class SubscriptMySuffix extends React.Component<ISubscriptMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { subscriptList, match } = this.props;
    return (
      <div>
        <h2 id="subscript-my-suffix-heading">
          Subscripts
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Subscript
          </Link>
        </h2>
        <div className="table-responsive">
          {subscriptList && subscriptList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Subscript Id</th>
                  <th>Subscript Code</th>
                  <th>Subscript Date</th>
                  <th>Customer Id</th>
                  <th>Customer Name</th>
                  <th>Unit Buy Price Ind</th>
                  <th>Unit Buy Price</th>
                  <th>Unit Buy Unit</th>
                  <th>Unit Buy Lot</th>
                  <th>Unit Buy Basket</th>
                  <th>Market</th>
                  <th>Buy Gross Amount</th>
                  <th>Fee</th>
                  <th>Buy Net Amount</th>
                  <th>Status Cash</th>
                  <th>Status Etf</th>
                  <th>Status</th>
                  <th>Channel</th>
                  <th>Dealer Id</th>
                  <th>Product Id</th>
                  <th>Underlying Id</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {subscriptList.map((subscript, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${subscript.id}`} color="link" size="sm">
                        {subscript.id}
                      </Button>
                    </td>
                    <td>{subscript.subscriptId}</td>
                    <td>{subscript.subscriptCode}</td>
                    <td>
                      <TextFormat type="date" value={subscript.subscriptDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{subscript.customerId}</td>
                    <td>{subscript.customerName}</td>
                    <td>{subscript.unitBuyPriceInd}</td>
                    <td>{subscript.unitBuyPrice}</td>
                    <td>{subscript.unitBuyUnit}</td>
                    <td>{subscript.unitBuyLot}</td>
                    <td>{subscript.unitBuyBasket}</td>
                    <td>{subscript.market}</td>
                    <td>{subscript.buyGrossAmount}</td>
                    <td>{subscript.fee}</td>
                    <td>{subscript.buyNetAmount}</td>
                    <td>{subscript.statusCash}</td>
                    <td>{subscript.statusEtf}</td>
                    <td>{subscript.status}</td>
                    <td>{subscript.channel}</td>
                    <td>{subscript.dealerId}</td>
                    <td>{subscript.productId}</td>
                    <td>{subscript.underlyingId}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${subscript.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${subscript.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${subscript.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Subscripts found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ subscript }: IRootState) => ({
  subscriptList: subscript.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SubscriptMySuffix);
