import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './subscript-my-suffix.reducer';
import { ISubscriptMySuffix } from 'app/shared/model/subscript-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISubscriptMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SubscriptMySuffixDetail extends React.Component<ISubscriptMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { subscriptEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Subscript [<b>{subscriptEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="subscriptId">Subscript Id</span>
            </dt>
            <dd>{subscriptEntity.subscriptId}</dd>
            <dt>
              <span id="subscriptCode">Subscript Code</span>
            </dt>
            <dd>{subscriptEntity.subscriptCode}</dd>
            <dt>
              <span id="subscriptDate">Subscript Date</span>
            </dt>
            <dd>
              <TextFormat value={subscriptEntity.subscriptDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="customerId">Customer Id</span>
            </dt>
            <dd>{subscriptEntity.customerId}</dd>
            <dt>
              <span id="customerName">Customer Name</span>
            </dt>
            <dd>{subscriptEntity.customerName}</dd>
            <dt>
              <span id="unitBuyPriceInd">Unit Buy Price Ind</span>
            </dt>
            <dd>{subscriptEntity.unitBuyPriceInd}</dd>
            <dt>
              <span id="unitBuyPrice">Unit Buy Price</span>
            </dt>
            <dd>{subscriptEntity.unitBuyPrice}</dd>
            <dt>
              <span id="unitBuyUnit">Unit Buy Unit</span>
            </dt>
            <dd>{subscriptEntity.unitBuyUnit}</dd>
            <dt>
              <span id="unitBuyLot">Unit Buy Lot</span>
            </dt>
            <dd>{subscriptEntity.unitBuyLot}</dd>
            <dt>
              <span id="unitBuyBasket">Unit Buy Basket</span>
            </dt>
            <dd>{subscriptEntity.unitBuyBasket}</dd>
            <dt>
              <span id="market">Market</span>
            </dt>
            <dd>{subscriptEntity.market}</dd>
            <dt>
              <span id="buyGrossAmount">Buy Gross Amount</span>
            </dt>
            <dd>{subscriptEntity.buyGrossAmount}</dd>
            <dt>
              <span id="fee">Fee</span>
            </dt>
            <dd>{subscriptEntity.fee}</dd>
            <dt>
              <span id="buyNetAmount">Buy Net Amount</span>
            </dt>
            <dd>{subscriptEntity.buyNetAmount}</dd>
            <dt>
              <span id="statusCash">Status Cash</span>
            </dt>
            <dd>{subscriptEntity.statusCash}</dd>
            <dt>
              <span id="statusEtf">Status Etf</span>
            </dt>
            <dd>{subscriptEntity.statusEtf}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{subscriptEntity.status}</dd>
            <dt>
              <span id="channel">Channel</span>
            </dt>
            <dd>{subscriptEntity.channel}</dd>
            <dt>
              <span id="dealerId">Dealer Id</span>
            </dt>
            <dd>{subscriptEntity.dealerId}</dd>
            <dt>
              <span id="productId">Product Id</span>
            </dt>
            <dd>{subscriptEntity.productId}</dd>
            <dt>
              <span id="underlyingId">Underlying Id</span>
            </dt>
            <dd>{subscriptEntity.underlyingId}</dd>
          </dl>
          <Button tag={Link} to="/entity/subscript-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/subscript-my-suffix/${subscriptEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ subscript }: IRootState) => ({
  subscriptEntity: subscript.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SubscriptMySuffixDetail);
